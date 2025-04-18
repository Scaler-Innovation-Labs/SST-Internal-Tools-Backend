package com.sstinternaltools.sstinternal_tools.security.service;

import jakarta.servlet.http.Cookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AuthService {
    public ResponseEntity<Map<String, String>> rotateRefreshToken(Cookie refreshCookie);
    public void logout(Cookie refreshCookie);
}
