package com.sstinternaltools.sstinternal_tools.security.service.interfaces;

import com.sstinternaltools.sstinternal_tools.user.entity.User;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;

public interface JwtService {
    String generateAccessToken(String email);
    String generateRefreshToken(String email);
    boolean isRefreshTokenValid(String token);
    void revokeRefreshToken(String token);
    void revokeAllTokensForUser(String email);
    String extractEmail(String token);
    boolean validateAccessToken(String token, User user);
}