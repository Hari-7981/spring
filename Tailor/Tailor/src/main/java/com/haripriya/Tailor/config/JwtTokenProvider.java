package com.haripriya.Tailor.config;

import io.jsonwebtoken.*; // Import all needed JWT classes
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    // It's better to get this from application.properties
    // @Value("${app.jwtSecret}")
    private final String jwtSecret = "RCJpnA63yLPtRXNNKt0dd9CBX0sETC9OZaSRd1Gpvfm0mfIUH+7Kr+wOZlhean+CMXZqod0Qr5oVHSlCnxky/Q=="; // MINIMUM 32 characters for HS256, preferably longer (e.g., 64 chars for HS512)
    // @Value("${app.jwtExpirationInMs}")
    private final long jwtExpirationMs = 86400000; // 1 day

    // HS512 requires a key length of 512 bits (64 bytes)
    // Using a securely generated key is crucial. For dev, a long string can work.
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }


    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key(), SignatureAlgorithm.HS512) // Use the generated Key
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key()) // Use the generated Key
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(authToken); // Use the generated Key
            return true;
        } catch (SignatureException ex) {
            // Invalid JWT signature
            System.err.println("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            // Invalid JWT token
            System.err.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            // Expired JWT token
            System.err.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            // Unsupported JWT token
            System.err.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            // JWT claims string is empty
            System.err.println("JWT claims string is empty.");
        }
        return false;
    }
}