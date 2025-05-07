package com.sstinternaltools.sstinternal_tools.security.service.implementation;

import com.sstinternaltools.sstinternal_tools.security.exception.JwtAuthenticationException;
import com.sstinternaltools.sstinternal_tools.security.service.interfaces.AuthService;
import com.sstinternaltools.sstinternal_tools.security.service.interfaces.JwtService;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import com.sstinternaltools.sstinternal_tools.user.repository.UserRepository;
import org.springframework.stereotype.Service;

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

        if (refreshToken == null || !refreshToken.startsWith("Bearer ")) {
            throw new JwtAuthenticationException("Invalid refresh token");
        }

        refreshToken = refreshToken.substring(7);

        if (!jwtService.isRefreshTokenValid(refreshToken)) {
            throw new JwtAuthenticationException("Expired or revoked refresh token");
        }

        String email = jwtService.extractEmail(refreshToken);
        jwtService.revokeRefreshToken(refreshToken);

        String newAccessToken = jwtService.generateAccessToken(email);
        String newRefreshToken = jwtService.generateRefreshToken(email);

        return Map.of("accessToken", newAccessToken, "refreshToken", newRefreshToken);
    }

    @Override
    public void logout(String refreshToken) {

        if (refreshToken == null || !refreshToken.startsWith("Bearer ")) {
            throw new JwtAuthenticationException("Invalid refresh token");
        }

        refreshToken = refreshToken.substring(7);

        if (!jwtService.isRefreshTokenValid(refreshToken)) {
            throw new JwtAuthenticationException("Expired or revoked refresh token");
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