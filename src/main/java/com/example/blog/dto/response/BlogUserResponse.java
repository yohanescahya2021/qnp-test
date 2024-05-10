package com.example.blog.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BlogUserResponse {

  @JsonProperty("id")
  private int id;

  @JsonProperty("firstname")
  private String firstName;

  @JsonProperty("lastname")
  private String lastName;

  @JsonProperty("email")
  private String email;

  @JsonProperty("birthdate")
  private String birthDate;

  @JsonProperty("Address")
  private BlogUserAddressResponse address;

  private BlogPostResponse post;
}