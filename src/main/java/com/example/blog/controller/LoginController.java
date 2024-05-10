package com.example.blog.controller;

import com.example.blog.dto.request.LoginRequest;
import com.example.blog.dto.response.LoginResponse;
import com.example.blog.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class LoginController {

  private final PasswordEncoder passwordEncoder;
  private final ReactiveUserDetailsService userDetailsService;
  private final TokenProvider tokenProvider;

  @PostMapping("/login")
  Mono<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
    return userDetailsService.findByUsername(loginRequest.getUsername())
        .filter(u -> passwordEncoder.matches(loginRequest.getPassword(), u.getPassword()))
        .map(tokenProvider::generateToken)
        .map(LoginResponse::new)
        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));
  }
}
