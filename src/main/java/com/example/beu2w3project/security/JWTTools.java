package com.example.beu2w3project.security;
import com.example.beu2w3project.exceptions.UnauthorizedOperationException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import com.example.beu2w3project.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {@Value("${jwt.secret}")
private String secret;
    @Value("${jwt.expiration}")
    private long expiration;

    public String createToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("role", user.getRole().name())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public void verifyToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedOperationException("Token non valido, effettua nuovamente il login.");
        }
    }

    public String extractUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build().parseSignedClaims(token).getPayload().getSubject();
    }
}

