package com.example.blog.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BlogPostResponse {
  private int id;
  private String slug;
  private String url;
  private String title;
  private String content;
  private String image;
  private String thumbnail;
  private String status;
  private String category;
  private String publishedAt;
  private String updatedAt;
  private int userId;
}
