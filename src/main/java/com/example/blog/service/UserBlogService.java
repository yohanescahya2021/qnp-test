package com.example.blog.service;

import com.example.blog.dto.response.AppResponse;
import reactor.core.publisher.Mono;

public interface UserBlogService {

  Mono<AppResponse> fetchUsersWithPost();
}
