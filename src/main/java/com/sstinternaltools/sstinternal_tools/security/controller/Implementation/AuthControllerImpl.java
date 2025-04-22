package com.sstinternaltools.sstinternal_tools.security.controller.Implementation;

import com.sstinternaltools.sstinternal_tools.security.controller.Interface.AuthController;
import com.sstinternaltools.sstinternal_tools.security.service.interfaces.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<Map<String, String>> rotateRefreshToken(@RequestHeader("Authorization") String refreshToken) {
        Map<String, String> map = authService.rotateRefreshToken(refreshToken);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @Override
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String refreshToken) {
        authService.logout(refreshToken);
        return ResponseEntity.ok("✅ User logged out successfully");
    }

    @GetMapping("/helloSecret")
    public ResponseEntity<String> helloSecret() {
        return ResponseEntity.ok("Hello World Secret");
    }

    @GetMapping("/")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World");
    }
}

