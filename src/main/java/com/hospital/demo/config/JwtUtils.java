package com.hospital.demo.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

@Component
public class JwtUtils {

    // Use HS512 algorithm directly from Jwts.SIG
    private final SecretKey SECRET_KEY = Jwts.SIG.HS512.key().build();
    private final long EXPIRATION_MS = 86400000; // 24 hours

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        
        return Jwts.builder()
            .claims(claims)  // New claims() method instead of setClaims()
            .subject(userDetails.getUsername())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
            .signWith(SECRET_KEY)  // Explicit algorithm specification
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return Jwts.parser()
            .verifyWith(SECRET_KEY)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }


    //old way of key

    // private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    // private final long EXPIRATION_MS = 86400000; // 24 hours

    // public String generateToken(UserDetails userDetails) {
    //     Map<String, Object> claims = new HashMap<>();
    //     return Jwts.builder()
    //         .setClaims(claims)
    //         .setSubject(userDetails.getUsername())
    //         .setIssuedAt(new Date())
    //         .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
    //         .signWith(SECRET_KEY)
    //         .compact();
    // }

    // public boolean validateToken(String token) {
    //     try {
    //         Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
    //         return true;
    //     } catch (JwtException | IllegalArgumentException e) {
    //         return false;
    //     }
    // }

    // public String extractUsername(String token) {
    //     return Jwts.parserBuilder()
    //         .setSigningKey(SECRET_KEY)
    //         .build()
    //         .parseClaimsJws(token)
    //         .getBody()
    //         .getSubject();
    // }

    
}
