package com.sstinternaltools.sstinternal_tools.security.service.interfaces;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AuthService {
    public ResponseEntity<Map<String, String>> rotateRefreshToken(Cookie refreshCookie, HttpServletResponse response);
    public void logout(Cookie refreshCookie);
}
