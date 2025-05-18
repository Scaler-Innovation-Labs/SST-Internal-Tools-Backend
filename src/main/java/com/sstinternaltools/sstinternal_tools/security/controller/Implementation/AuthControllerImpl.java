package com.sstinternaltools.sstinternal_tools.security.controller.Implementation;

import com.sstinternaltools.sstinternal_tools.security.controller.Interface.AuthController;
import com.sstinternaltools.sstinternal_tools.security.service.interfaces.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Map<String, String>> rotateRefreshToken(@CookieValue("refreshToken") String refreshToken, HttpServletResponse response) {
        return authService.rotateRefreshToken(refreshToken, response);
    }

    @Override
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@CookieValue("refreshToken") String refreshToken) {
        authService.logout(refreshToken);
        return ResponseEntity.ok("✅ User logged out successfully");
    }

}
