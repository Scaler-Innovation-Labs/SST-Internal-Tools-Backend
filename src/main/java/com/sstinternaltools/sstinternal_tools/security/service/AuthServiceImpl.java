package com.sstinternaltools.sstinternal_tools.security.service;

import com.sstinternaltools.sstinternal_tools.security.exception.JwtAuthenticationException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;

    public AuthServiceImpl(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<Map<String, String>> rotateRefreshToken(Cookie refreshCookie,  HttpServletResponse response) {
        String refreshToken = refreshCookie.getValue();

        if (refreshToken == null || !jwtService.isRefreshTokenValid(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid or expired refresh token"));
        }

        String email = jwtService.extractEmail(refreshToken);
        jwtService.revokeRefreshToken(refreshToken);

        String newAccessToken = jwtService.generateAccessToken(email);
        Cookie newRefreshToken = jwtService.generateRefreshToken(email);

        response.addCookie(newRefreshToken);
        return ResponseEntity
                .ok(Map.of(
                "accessToken", newAccessToken
        ));
    }

    @Override
    public void logout(Cookie refreshCookie) {
        String refreshToken = refreshCookie.getValue();

        if (refreshToken == null || !jwtService.isRefreshTokenValid(refreshToken)) {
            throw new JwtAuthenticationException("Invalid or expired refresh token");
        }

        jwtService.revokeRefreshToken(refreshToken);
    }
}
