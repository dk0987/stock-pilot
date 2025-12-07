package com.sp.auth_service.config;

import com.sp.auth_service.dto.UserGRPCResponseDTO;
import com.sp.auth_service.dto.UserResponseDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JWTUtil {

    private static final String SECRET = "your-256-bit-secret-your-256-bit-secret";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // Generate JWT
    public String generateToken(UserResponseDTO userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUserId().toString())
                .claim("authority", userDetails.getAllowedAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000)) // 1 hour
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract all claims - supports expired token
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (ExpiredJwtException e) {
            return e.getClaims(); // still safe because signature is verified
        }
    }

    // Extract userId from subject
    public Long extractUserIdFromToken(String token) {
        return Long.valueOf(extractAllClaims(token).getSubject());
    }

    // Extract authorities (Set<Long>)
    public Set<Long> extractAllowedAuthorities(String token) {
        Claims claims = extractAllClaims(token);
        List<Integer> authorities = claims.get("authority", List.class);

        if (authorities == null) return Collections.emptySet();

        return authorities.stream()
                .map(Long::valueOf)
                .collect(Collectors.toSet());
    }

    // Expiration check (safe)
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // Token Validation
    public void validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
        }
        catch (ExpiredJwtException e) {
            throw new RuntimeException("token expired");
        } catch (JwtException e) {
            throw new RuntimeException("token invalid");
        }
    }
}
