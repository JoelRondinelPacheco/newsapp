package com.joel.newsapp.utils;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {

    private String secret = "secret";

    public String extractUsername(String token) {
        retrun
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public Claims extractAllClaims(String token) {
        return Jwts.p
    }

}
