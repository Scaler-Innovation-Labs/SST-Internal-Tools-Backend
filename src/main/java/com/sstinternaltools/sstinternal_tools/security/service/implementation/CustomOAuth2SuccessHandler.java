package com.sstinternaltools.sstinternal_tools.security.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sstinternaltools.sstinternal_tools.security.exception.InvalidCredentialsException;
import com.sstinternaltools.sstinternal_tools.security.service.interfaces.AuthService;
import com.sstinternaltools.sstinternal_tools.security.service.interfaces.CustomLogicService;
import com.sstinternaltools.sstinternal_tools.security.service.interfaces.JwtService;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import com.sstinternaltools.sstinternal_tools.user.entity.UserRole;
import com.sstinternaltools.sstinternal_tools.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final CustomLogicService customLogicService;
    private final AuthService authService;

    public CustomOAuth2SuccessHandler(JwtService jwtService,
                                      UserRepository userRepository,
                                      CustomLogicService customLogicService,
                                      AuthService authService,
                                      ObjectMapper ignored) {          // ObjectMapper not needed here
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.customLogicService = customLogicService;
        this.authService = authService;
    }

    @Override public void onAuthenticationSuccess(HttpServletRequest req,
                                                  HttpServletResponse res,
                                                  Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        String domain = email.split("@")[1];
        if (!domain.equals("sst.scaler.com") && !domain.equals("scaler.com"))
            throw new InvalidCredentialsException("Invalid domain: " + domain);

        /* 1️⃣  Auto-register new users + role assignment (unchanged) */
        if (userRepository.findByEmail(email).isEmpty()) {
            authService.register(email);
            List<UserRole> roles = customLogicService.assignRoles(email);
            if (roles.isEmpty()) throw new InvalidCredentialsException("Access denied");
            User user = userRepository.findByEmail(email).get();
            user.setUserRoles(roles);
            userRepository.save(user);
        }

        /* 2️⃣  Issue tokens */
        String access  = jwtService.generateAccessToken(email);
        String refresh = jwtService.generateRefreshToken(email);

        /* 3️⃣  Send them as secure, HTTP-only cookies */
        ResponseCookie accessCookie = ResponseCookie.from("accessToken", access)
                .httpOnly(true).secure(true).sameSite("Lax")
                .path("/api/")                         // sent only to API routes
                .maxAge(Duration.ofMinutes(15))
                .build();

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refresh)
                .httpOnly(true).secure(true).sameSite("Strict")
                .path("/auth/refresh")                 // only to refresh endpoint
                .maxAge(Duration.ofDays(30))
                .build();

        res.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        res.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        /* 4️⃣  Land the SPA back on its root */
        res.sendRedirect("/");
    }
}
