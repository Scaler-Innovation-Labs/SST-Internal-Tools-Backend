package com.sstinternaltools.sstinternal_tools.security.service;

import com.sstinternaltools.sstinternal_tools.security.entity.JwtToken;
import com.sstinternaltools.sstinternal_tools.security.entity.TokenType;
import com.sstinternaltools.sstinternal_tools.security.repository.JwtTokenRepository;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import com.sstinternaltools.sstinternal_tools.user.entity.UserRole;
import com.sstinternaltools.sstinternal_tools.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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

    //generates encoded secret key to sign the tokens
    private SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    //method to generate access token
    public String generateAccessToken(String email) {

        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User with email " + email + " not found."));

        List<UserRole> role=user.getUserRoles();
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+accessTokenExpiration))
                .and()
                .signWith(getKey())
                .compact();
    }

    //method to generate refresh token
    public String generateRefreshToken(String email) {
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("User with email " + email + " not found."));

        String token= Jwts.builder()
                .claims()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+refreshTokenExpiration))
                .and()
                .signWith(getKey())
                .compact();

        JwtToken refreshToken= new JwtToken(
                token,
                TokenType.REFRESH,
                false,
                false,
                LocalDateTime.now().plusSeconds(refreshTokenExpiration/1000),
                user
        );
        jwtTokenRepository.save(refreshToken);
        return token;
    }

    //method to extract the email stored inside jwt
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims =extractAllClaims(token); //now claim holds all the data inside the Jwt
        return claimsResolver.apply(claims); //we apply claim resolver function to extract one specific claim
    }

    //generic method to extract claims from the token
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser().verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new JwtAuthenticationException("Error in extracting claims");
        }
    }

    //method to validate refresh token
    public boolean isRefreshTokenValid(String token){
        return jwtTokenRepository.findByToken(token)
                .filter(t-> !t.isRevoked() && !t.isExpired() && t.getTokenType()==TokenType.REFRESH)
                .filter(t->t.getExpirationDateTime().isAfter(LocalDateTime.now()))
                .isPresent();
    }

    //method to revoke Refresh token
    public void revokeRefreshToken(String token){
        jwtTokenRepository.findByToken(token).ifPresent(
                t->{
                    t.setRevoked(true);
                    jwtTokenRepository.save(t);
                }
        );
    }

    //method to revoke all the refresh tokens for the user
    public void revokeAllTokensForUser(String email){
        List<JwtToken> tokens=jwtTokenRepository.findAllTokensByUser_Email(email);
        tokens.forEach(token-> token.setRevoked(true));
        jwtTokenRepository.saveAll(tokens);
    }

    //method to validate access token
    public boolean validateAccessToken(String token, User user) {
        final String email = extractEmail(token);
        return (email.equals(user.getEmail()) && !isTokenExpired(token));
    }

    //method to check the token is expired or not
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


}
