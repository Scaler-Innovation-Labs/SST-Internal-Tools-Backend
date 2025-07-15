package com.sstinternaltools.sstinternal_tools.security.service.implementation;

import com.sstinternaltools.sstinternal_tools.security.exception.JwtAuthenticationException;
import com.sstinternaltools.sstinternal_tools.security.service.interfaces.AuthService;
import com.sstinternaltools.sstinternal_tools.security.service.interfaces.JwtService;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import com.sstinternaltools.sstinternal_tools.user.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthServiceImpl(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public void rotateRefreshToken(String refreshToken, HttpServletResponse response) {

        if (refreshToken == null) {
            throw new JwtAuthenticationException("Invalid refresh token");
        }

        if (!jwtService.isRefreshTokenValid(refreshToken)) {
            throw new JwtAuthenticationException("Expired or revoked refresh token");
        }

        String email = jwtService.extractEmail(refreshToken);
        jwtService.revokeRefreshToken(refreshToken);

        ResponseCookie newAccessToken = jwtService.generateAccessCookie(email);
        ResponseCookie newRefreshToken = jwtService.generateRefreshCookie(email);

        response.addHeader("Set-Cookie", newAccessToken.toString());
        response.addHeader("Set-Cookie", newRefreshToken.toString());

    }

    @Override
    public void logout(String refreshToken) {

        if (refreshToken == null) {
            throw new JwtAuthenticationException("Invalid refresh token");
        }

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