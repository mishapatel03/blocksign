package com.blocksign.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

  @Bean
public SecurityWebFilterChain filterChain(
    ServerHttpSecurity http) {
  return http
    .csrf(csrf -> csrf.disable())
    .cors(cors -> cors.configurationSource(
        corsConfigurationSource()))
    .authorizeExchange(ex -> ex.anyExchange().permitAll())
    .build();
}

@Bean
public CorsConfigurationSource corsConfigurationSource() {
  CorsConfiguration config = new CorsConfiguration();
  config.setAllowedOrigins(List.of(
      "http://localhost:4200",
      "https://https://blocksign-1qilt7wdn-mishapatel.vercel.app"
  ));
  config.setAllowedMethods(List.of(
      "GET", "POST", "PUT", "DELETE", "OPTIONS"));
  config.setAllowedHeaders(List.of("*"));
  config.setAllowCredentials(true);
  
  UrlBasedCorsConfigurationSource source = 
      new UrlBasedCorsConfigurationSource();
  source.registerCorsConfiguration("/**", config);
  return source;
}
}
