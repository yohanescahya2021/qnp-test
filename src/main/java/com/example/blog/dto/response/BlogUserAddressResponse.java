package com.example.blog.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BlogUserAddressResponse {
  private String street;
  private String suite;
  private String city;
  private String zipcode;
}
