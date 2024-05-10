package com.example.blog.service.impl;

import com.example.blog.dto.response.AppResponse;
import com.example.blog.dto.response.BlogPostResponse;
import com.example.blog.dto.response.BlogUserResponse;
import com.example.blog.service.UserBlogService;
import com.example.blog.webclient.JsonPlaceHolderClient;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserBlogServiceImpl implements UserBlogService {

  private final JsonPlaceHolderClient jsonPlaceHolderClient;


  @Override
  public Mono<AppResponse> fetchUsersWithPost() {

    return Mono.just(AppResponse.builder()
            .startTime(LocalDateTime.now())
            .build()
        )
        .flatMap(appResponse -> jsonPlaceHolderClient.fetchUser()
            .collectList()
            .flatMap(blogUserResponses -> Flux.fromIterable(blogUserResponses)
                .flatMap(blogUserResponse -> jsonPlaceHolderClient.fetchPostByUserId(
                            blogUserResponse.getId()
                        )
                        .doOnNext(blogUserResponse::setPost)
                )
                .collectList()
                .flatMap(blogPostResponses -> Mono.just(blogUserResponses))
            )
            .doOnNext(appResponse::setResponses)
            .doOnNext(blogUserResponses -> appResponse.setEndTime(LocalDateTime.now()))
            .thenReturn(appResponse)
        );
  }
}
