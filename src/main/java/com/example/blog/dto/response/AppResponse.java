package com.example.blog.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AppResponse {
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private List<BlogUserResponse> responses;
}
