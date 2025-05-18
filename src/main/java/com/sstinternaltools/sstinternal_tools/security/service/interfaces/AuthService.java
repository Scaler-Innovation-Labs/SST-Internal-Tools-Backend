package com.sstinternaltools.sstinternal_tools.security.service.interfaces;

import java.util.Map;

public interface AuthService {
    public ResponseEntity<Map<String, String>> rotateRefreshToken(String refreshToken, HttpServletResponse response);
    public void logout(String refreshToken);
    void register(String email);
}