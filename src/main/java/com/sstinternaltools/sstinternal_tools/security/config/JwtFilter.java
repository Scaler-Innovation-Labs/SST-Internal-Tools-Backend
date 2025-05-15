package com.sstinternaltools.sstinternal_tools.security.config;

import com.sstinternaltools.sstinternal_tools.security.exception.JwtAuthenticationException;
import com.sstinternaltools.sstinternal_tools.security.exception.UserNotFoundException;
import com.sstinternaltools.sstinternal_tools.security.service.interfaces.JwtService;
import com.sstinternaltools.sstinternal_tools.security.service.interfaces.MyUserDetailService;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import com.sstinternaltools.sstinternal_tools.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final MyUserDetailService myUserDetailService;

    public JwtFilter(JwtService jwtService,
                     UserRepository userRepository,
                     MyUserDetailService myUserDetailService) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.myUserDetailService = myUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String path = request.getServletPath();
        System.out.println("Request path: " + path);

        /* 1️⃣  Bypass EVERYTHING that is not an API endpoint */
        if (!path.startsWith("/api/")) {          // <— key change
            chain.doFilter(request, response);
            return;
        }

        /* 2️⃣  Try header first, then secure cookie */
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null) {
            Cookie cookie = WebUtils.getCookie(request, "accessToken");
            if (cookie != null)
                authHeader = "Bearer " + cookie.getValue();
        }
        System.out.println("🛂  -> Authorization = " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);   // leads to clean 401 JSON
            return;
        }

        String token = authHeader.substring(7);
        String userEmail;
        try {
            userEmail = jwtService.extractEmail(token);
        } catch (Exception e) {
            throw new JwtAuthenticationException("Invalid JWT token.");
        }

        if (userEmail != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            User user = userRepository.findByEmailWithRoles(userEmail)
                    .orElseThrow(() ->
                            new UserNotFoundException("User not found: " + userEmail));

            if (!jwtService.validateAccessToken(token, user)) {
                throw new JwtAuthenticationException("Invalid or expired access token.");
            }

            UserDetails ud = myUserDetailService.loadUserByEmail(userEmail);
            UsernamePasswordAuthenticationToken at =
                    new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities());
            at.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(at);
        }

        chain.doFilter(request, response);
    }
}
