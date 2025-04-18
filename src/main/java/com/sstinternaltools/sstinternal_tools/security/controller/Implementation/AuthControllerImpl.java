package com.sstinternaltools.sstinternal_tools.security.controller.Implementation;

import com.sstinternaltools.sstinternal_tools.security.controller.Interface.AuthController;
import com.sstinternaltools.sstinternal_tools.security.service.interfaces.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> rotateRefreshToken(@CookieValue("refreshToken") Cookie refreshCookie, HttpServletResponse response) {
        return authService.rotateRefreshToken(refreshCookie, response);
    }

    @Override
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@CookieValue("refreshToken") Cookie refreshCookie) {
        authService.logout(refreshCookie);
        return ResponseEntity.ok("✅ User logged out successfully");
    }
}

