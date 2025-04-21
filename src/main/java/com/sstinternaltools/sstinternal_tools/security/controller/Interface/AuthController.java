package com.sstinternaltools.sstinternal_tools.security.controller.Interface;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

public interface AuthController {
    public ResponseEntity<Map<String, String>> rotateRefreshToken (@PathVariable String refreshToken);
    public ResponseEntity<String> logout(@PathVariable String refreshToken);
}
