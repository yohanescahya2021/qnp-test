package com.example.blog.webclient;

import com.example.blog.dto.response.BlogPostResponse;
import com.example.blog.dto.response.BlogUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class JsonPlaceHolderClient {

  private final WebClient jsonWebClient;

  public Flux<BlogUserResponse> fetchUser() {
    return jsonWebClient.get()
        .uri("/users/")
        .retrieve()
        .bodyToFlux(BlogUserResponse.class)
        .doOnError(throwable -> {
              log.error(throwable.getMessage());
              Mono.empty();
            }
        );
  }

  public Mono<BlogPostResponse> fetchPostByUserId(Integer userId) {
    return jsonWebClient.get()
        .uri("/posts/" + userId)
        .retrieve()
        .bodyToMono(BlogPostResponse.class)
        .doOnError(throwable -> {
              log.error(throwable.getMessage());
              Mono.empty();
            }
        );
  }
}
