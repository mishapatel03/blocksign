package com.blocksign.auth.controller;

import com.blocksign.auth.dto.request.LoginRequest;
import com.blocksign.auth.dto.request.RegisterRequest;
import com.blocksign.auth.dto.response.AuthResponse;
import com.blocksign.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
    log.info("Register request for email: {}", request.getEmail());
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(authService.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
    log.info("Login request for email: {}", request.getEmail());
    return ResponseEntity.ok(authService.login(request));
  }
}
