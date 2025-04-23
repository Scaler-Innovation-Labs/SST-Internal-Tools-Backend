package com.sstinternaltools.sstinternal_tools.security.config;

import com.sstinternaltools.sstinternal_tools.security.exception.JwtAuthenticationException;
import com.sstinternaltools.sstinternal_tools.security.exception.UserNotFoundException;
import com.sstinternaltools.sstinternal_tools.security.service.interfaces.JwtService;
import com.sstinternaltools.sstinternal_tools.security.service.interfaces.MyUserDetailService;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import com.sstinternaltools.sstinternal_tools.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final MyUserDetailService myUserDetailsService;

    public JwtFilter(JwtService jwtService, UserRepository userRepository, MyUserDetailService myUserDetailsService) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);
        final String userEmail;

        try {
            userEmail = jwtService.extractEmail(token);
        } catch (Exception e) {
            throw new JwtAuthenticationException("Invalid JWT token.");
        }

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userRepository.findByEmailWithRoles(userEmail)
                    .orElseThrow(() -> new UserNotFoundException("User not found with email: " + userEmail));

            if (!jwtService.validateAccessToken(token, user)) {
                throw new JwtAuthenticationException("Invalid or expired access token.");
            }

            UserDetails userDetails = myUserDetailsService.loadUserByEmail(userEmail);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }
}
