package com.sstinternaltools.sstinternal_tools.security.service.implementation;

import com.sstinternaltools.sstinternal_tools.security.exception.JwtAuthenticationException;
import com.sstinternaltools.sstinternal_tools.security.service.interfaces.AuthService;
import com.sstinternaltools.sstinternal_tools.security.service.interfaces.JwtService;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import com.sstinternaltools.sstinternal_tools.user.entity.UserRole;
import com.sstinternaltools.sstinternal_tools.user.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthServiceImpl(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public Map<String, String> rotateRefreshToken(String refreshToken) {

        if (refreshToken == null || !jwtService.isRefreshTokenValid(refreshToken)) {
            return Map.of("error", "Invalid or expired refresh token");
        }

        String email = jwtService.extractEmail(refreshToken);
        jwtService.revokeRefreshToken(refreshToken);

        String newAccessToken = jwtService.generateAccessToken(email);
        String newRefreshToken = jwtService.generateRefreshToken(email);

        return Map.of("accessToken", newAccessToken, "refreshToken", newRefreshToken);
    }

    @Override
    public void logout(String refreshToken) {

        if (refreshToken == null || !jwtService.isRefreshTokenValid(refreshToken)) {
            throw new JwtAuthenticationException("Invalid or expired refresh token");
        }

        jwtService.revokeRefreshToken(refreshToken);
    }

    @Override
    public void register(String email) {
        User user = new User();
        user.setEmail(email);
        userRepository.save(user);
    }
}
