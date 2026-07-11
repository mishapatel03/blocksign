package com.blocksign.auth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private Long expiration;

  private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(secret.getBytes());
  }

  public String generateToken(Long userId, String email, String tenantId) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", userId);
    claims.put("email", email);
    claims.put("tenantId", tenantId);
    return createToken(claims, email);
  }

  private String createToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
        .claims(claims)
        .subject(subject)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(getSigningKey())
        .compact();
  }

  public Claims extractClaims(String token) {
    return Jwts.parser()
        .verifyWith(getSigningKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  public String extractEmail(String token) {
    return extractClaims(token).getSubject();
  }

  public Long extractUserId(String token) {
    return extractClaims(token).get("userId", Long.class);
  }

  public String extractTenantId(String token) {
    return extractClaims(token).get("tenantId", String.class);
  }

  public Boolean isTokenExpired(String token) {
    return extractClaims(token).getExpiration().before(new Date());
  }

  public Boolean validateToken(String token, String email) {
    final String extractedEmail = extractEmail(token);
    return (extractedEmail.equals(email) && !isTokenExpired(token));
  }
}
