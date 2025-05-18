package com.sstinternaltools.sstinternal_tools.security.service.interfaces;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    public void rotateRefreshToken(String refreshToken, HttpServletResponse response);
    public void logout(String refreshToken);
    void register(String email);
}