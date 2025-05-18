package com.sstinternaltools.sstinternal_tools.security.service.interfaces;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    void rotateRefreshToken(String refreshToken, HttpServletResponse response);
    void logout(String refreshToken);
    void register(String email);
}