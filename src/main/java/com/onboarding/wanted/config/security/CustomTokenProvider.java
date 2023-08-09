package com.onboarding.wanted.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.onboarding.wanted.config.security.JwtConstants.*;
import static com.onboarding.wanted.config.security.JwtConstants.REFRESH_TOKEN_VALIDATION_SECOND;

@Component
@RequiredArgsConstructor
public class CustomTokenProvider {
    @Value("{jwt.secret.key}")
    private String SECRET_KEY;

    public String generateAccessToken(CustomUserDetail member) {
        return Jwts.builder()
                .setHeader(createHeader())
                .setClaims(createClaims(member))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDATION_SECOND))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String generateRefreshToken(CustomUserDetail member) {
        return Jwts.builder()
                .setHeader(createHeader())
                .setClaims(createClaims(member))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDATION_SECOND))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    private Map<String, Object> createHeader() {
        HashMap<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        return header;
    }

    private Claims createClaims(CustomUserDetail member) {
        Claims claims = Jwts.claims();
        claims.put("username", member.getUsername());
        claims.put("roles", member.getAuthorities());

        return claims;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private String getUsername(String token) {
        return extractAllClaims(token).get("username", String.class);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey(SECRET_KEY))
                .parseClaimsJws(token)
                .getBody();
    }

    private byte[] getSigningKey(String secretKey) {
        return secretKey.getBytes(StandardCharsets.UTF_8);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }
}