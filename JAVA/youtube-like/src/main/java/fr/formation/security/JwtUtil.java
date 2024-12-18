package fr.formation.security;

import java.util.Date;

import javax.crypto.SecretKey;

import fr.formation.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    private static final String KEY = "hTcmSxxrWqpB2Ze8sU2WvnF4z8gu3HLEEXZdo48UsF8eRs8HtC6kOz4Zn3MIsXY1";

    private JwtUtil() { }

    public static String generate(User user) {
        SecretKey key = Keys.hmacShaKeyFor(KEY.getBytes());
        Date now = new Date();

        return Jwts.builder()
            .subject(user.getUsername())
            .issuedAt(now)
            .expiration(new Date(now.getTime() + 36_000_000))
            .claim("user-id", user.getId())
            .signWith(key)
            .compact();
    }

    public static Jwt parse(String token) {
        if (token == null) {
            return Jwt.builder()
                .valid(false)
                .build()
            ;
        }
        
        try {
            SecretKey key = Keys.hmacShaKeyFor(KEY.getBytes());
            
            Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
            
            return Jwt.builder()
                .valid(true)
                .username(claims.getSubject())
                .userId(claims.get("user-id", String.class))
                .build()
            ;
        }

        catch (Exception ex) {
            return Jwt.builder()
                .valid(false)
                .build()
            ;
        }
    }
}
