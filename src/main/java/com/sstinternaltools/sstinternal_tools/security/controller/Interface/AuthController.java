package com.sstinternaltools.sstinternal_tools.security.controller.Interface;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AuthController {
    public ResponseEntity<Map<String, String>> rotateRefreshToken (String refreshToken, HttpServletResponse response);
    public ResponseEntity<String> logout(String refreshToken);
}