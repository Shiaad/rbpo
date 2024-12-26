package com.mtuci.rbpo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtils {

    public Key createSigningKey(String secret) {
        if (secret == null || secret.length() < 32) {
            throw new IllegalArgumentException("Secret key must be at least 32 characters long");
        }
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims parseClaims(String token, Key signingKey) {
        try {
            return Jwts.parserBuilder()
                       .setSigningKey(signingKey)
                       .build()
                       .parseClaimsJws(token)
                       .getBody();
        } catch (JwtException ex) {
            throw new IllegalArgumentException("Failed to parse token: " + ex.getMessage());
        }
    }
}
