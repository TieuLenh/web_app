package backend.webapp.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET =
            "my_super_secret_key_123456_my_super_secret_key_123456";

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(
                SECRET.getBytes(StandardCharsets.UTF_8)
        );
    }

    // =========================
    // 1. GENERATE TOKEN
    // =========================
    public String generateToken(Long accountId, String role) {

        return Jwts.builder()
                .setSubject(String.valueOf(accountId)) // 👈 PRIMARY KEY (quan trọng nhất)
                .claim("role", role)                   // optional
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60) // 1h
                )
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // =========================
    // 2. EXTRACT ACCOUNT ID
    // =========================
    public Long extractAccountId(String token) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    // =========================
    // 3. OPTIONAL EXTRACT USER INFO
    // =========================

    public String extractRole(String token) {
        Claims claims = getAllClaims(token);
        return claims.get("role", String.class);
    }

    // =========================
    // 4. COMMON METHOD
    // =========================
    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}