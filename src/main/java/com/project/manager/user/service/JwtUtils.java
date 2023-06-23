package com.project.manager.user.service;

import com.project.manager.user.service.impl.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class JwtUtils {
        private  String secret ;
    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        long expirationTimeInMs = TimeUnit.HOURS.toMillis(12);; // 12 hour
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        secret = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        SecretKey convertedKey = new SecretKeySpec(keyBytes, "HmacSHA256");
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userPrincipal.getUsername());
        claims.put("role", userPrincipal.getAuthorities());

        return  Jwts.builder()
                .setClaims(claims)
                .setSubject(userPrincipal.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMs))
                .signWith(convertedKey)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
        return (String) claims.get("username");
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
    }