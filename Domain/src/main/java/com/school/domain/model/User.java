package com.school.domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

  private Long id;
  private String name;
  private String address;
  private int age;
  private Integer phone;
  private String email;
  private String password;
  private String role;
  private String status;
}
