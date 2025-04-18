package com.sstinternaltools.sstinternal_tools.security.controller.Interface;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;

import java.util.Map;

public interface AuthController {
    public ResponseEntity<Map<String, String>> rotateRefreshToken (@CookieValue("refreshToken") Cookie refreshCookie, HttpServletResponse response);
    public ResponseEntity<String> logout(@CookieValue("refreshToken") Cookie refreshCookie);
}
