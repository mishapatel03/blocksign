package com.blocksign.auth.service;

import com.blocksign.auth.dto.request.LoginRequest;
import com.blocksign.auth.dto.request.RegisterRequest;
import com.blocksign.auth.dto.response.AuthResponse;
import com.blocksign.auth.entity.User;
import com.blocksign.auth.exception.AccountDisabledException;
import com.blocksign.auth.exception.InvalidCredentialsException;
import com.blocksign.auth.exception.UserAlreadyExistsException;
import com.blocksign.auth.repository.UserRepository;
import com.blocksign.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  public AuthResponse register(RegisterRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new UserAlreadyExistsException("Email already registered. Please use a different email or login.");
    }

    User user = new User();
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setName(request.getName());
    user.setTenantId(UUID.randomUUID().toString());
    user.setEnabled(true);

    user = userRepository.save(user);

    String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getTenantId());

    return new AuthResponse(token, "Bearer", user.getId(), user.getEmail(), user.getName(), user.getTenantId());
  }

  public AuthResponse login(LoginRequest request) {
    User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new InvalidCredentialsException("Invalid email or password");
    }

    if (!user.getEnabled()) {
      throw new AccountDisabledException("Account is disabled. Please contact support.");
    }

    String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getTenantId());

    return new AuthResponse(token, "Bearer", user.getId(), user.getEmail(), user.getName(), user.getTenantId());
  }
}
