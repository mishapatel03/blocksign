package com.blocksign.gateway.filter;

import com.blocksign.gateway.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter implements GlobalFilter, Ordered {

  private final JwtUtil jwtUtil;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    String path = exchange.getRequest().getPath().value();

    // Skip authentication for auth endpoints
    if (path.startsWith("/api/auth")) {
      return chain.filter(exchange);
    }

    String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
      return exchange.getResponse().setComplete();
    }

    String token = authHeader.substring(7);

    if (!jwtUtil.validateToken(token)) {
      exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
      return exchange.getResponse().setComplete();
    }

    // Add user info to headers for downstream services
    String userId = jwtUtil.extractUserId(token).toString();
    String email = jwtUtil.extractEmail(token);
    String tenantId = jwtUtil.extractTenantId(token);

    ServerWebExchange mutatedExchange = exchange.mutate()
        .request(r -> r.header("X-User-Id", userId)
            .header("X-User-Email", email)
            .header("X-Tenant-Id", tenantId))
        .build();

    return chain.filter(mutatedExchange);
  }

  @Override
  public int getOrder() {
    return -100;
  }
}
