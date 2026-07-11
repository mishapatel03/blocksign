package com.blocksign.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

  @Value("${jwt.secret}")
  private String secret;

  private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(secret.getBytes());
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

  public Boolean validateToken(String token) {
    try {
      return !isTokenExpired(token);
    } catch (Exception e) {
      return false;
    }
  }
}
