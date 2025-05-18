package com.sstinternaltools.sstinternal_tools.security.controller.Interface;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AuthController {
    public ResponseEntity<String> rotateRefreshToken (String refreshToken, HttpServletResponse response);
    public ResponseEntity<String> logout(String refreshToken);
}