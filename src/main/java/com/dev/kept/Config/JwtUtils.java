package com.dev.kept.Config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final long EXPIRATION_TIME = 1800000;


    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
        .setSubject(((CustomUserDetails) userDetails).getUsername())
        .setIssuedAt( new Date())
        .setExpiration( new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS256).compact();
    }

    public String extractSubject(String token) {
        return Jwts.parserBuilder()
        .setSigningKey(jwtSecret.getBytes())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return extractSubject(token).equals(userDetails.getUsername());
    }



}
