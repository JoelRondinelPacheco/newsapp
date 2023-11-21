package com.joel.newsapp.services;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtTokenService {
    private SecretKey key = Jwts.SIG.HS256.key().build();
    private static final long DURACION_TOKEN_MILISEGUNDOS = 3 * 24 * 60 * 60 * 1000; // 3 días en milisegundos

    public String passwordTokenGenerator(String email, boolean valid) {
        Date expirationDate = new Date(System.currentTimeMillis() + DURACION_TOKEN_MILISEGUNDOS);
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .claim("valid", valid)
                .signWith(key)
                .compact();
    }

    public boolean passwordTokenValid(String token) {
        try {
            this.verifyToken(token);
            Claims claims = this.getAllClaims(token);
            boolean active = claims.get("valid", Boolean.class);
            if (active) return true;
            return false;
        } catch (JwtException e) {
            return false;
        } catch (Exception e) {
            // Manejo de errores, el token no es válido
            return false;
        }
    }

    public boolean verifyToken(String token) throws JwtException {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            throw e;
        }
    }
    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
