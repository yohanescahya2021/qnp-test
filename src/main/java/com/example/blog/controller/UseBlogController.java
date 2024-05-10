package com.example.blog.controller;

import com.example.blog.dto.response.AppResponse;
import com.example.blog.service.UserBlogService;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/userblog")
@RequiredArgsConstructor
public class UseBlogController {

  private final UserBlogService userBlogService;

  @GetMapping("/fetchAllUsers")
  @RateLimiter(name = "userpost-service", fallbackMethod = "fallbackMethod")
  public Mono<AppResponse> fetchAllUsers() {
    return userBlogService.fetchUsersWithPost();
  }

  private Mono<ResponseEntity<String>> fallbackMethod(RequestNotPermitted requestNotPermitted) {
    return Mono.just(ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many request"));
  }
}
