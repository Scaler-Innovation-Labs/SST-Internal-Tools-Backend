package com.sstinternaltools.sstinternal_tools.security.service;

import com.sstinternaltools.sstinternal_tools.security.repository.JwtTokenRepository;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import com.sstinternaltools.sstinternal_tools.user.entity.UserRole;
import com.sstinternaltools.sstinternal_tools.user.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtService {

    private String jwtSecretKey;
    private long accessTokenExpiration;
    private long refreshTokenExpiration;
    private final JwtTokenRepository jwtTokenRepository;
    private final UserRepository userRepository;

    public JwtService(JwtTokenRepository jwtTokenRepository,UserRepository userRepository,@Value("${jwt.secretKey}") String jwtSecretKey,@Value("${jwt.access_expiration}") long accessTokenExpiration,@Value("{jwt.refresh_expiration}") long refreshTokenExpiration) {
        this.jwtTokenRepository = jwtTokenRepository;
        this.userRepository = userRepository;
        this.jwtSecretKey=jwtSecretKey;
        this.accessTokenExpiration=accessTokenExpiration;
        this.refreshTokenExpiration=refreshTokenExpiration;
    }

    public String generateAccessToken(String email) {
        //Loading user using userEmail
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User with email " + email + " not found."));

        List<UserRole> role=user.get


        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
    }
}
