package com.sstinternaltools.sstinternal_tools.security.service.interfaces;

import com.sstinternaltools.sstinternal_tools.user.entity.User;
import jakarta.servlet.http.Cookie;

public interface JwtService {
    String generateAccessToken(String email);
    Cookie generateRefreshToken(String email);
    boolean isRefreshTokenValid(String token);
    void revokeRefreshToken(String token);
    void revokeAllTokensForUser(String email);
    String extractEmail(String token);
    boolean validateAccessToken(String token, User user);
}
