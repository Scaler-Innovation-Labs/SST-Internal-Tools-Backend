package com.sstinternaltools.sstinternal_tools.security.controller.Interface;

import jakarta.servlet.http.Cookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;

import java.util.Map;

public interface AuthController {
    public ResponseEntity<Map<String, String>> rotateRefreshToken (@CookieValue("refreshToken") Cookie refreshCookie);
    public ResponseEntity<String> logout(@CookieValue("refreshToken") Cookie refreshCookie);
}
