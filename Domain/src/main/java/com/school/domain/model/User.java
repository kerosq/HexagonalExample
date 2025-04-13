package com.school.domain.model;

import java.time.OffsetDateTime;
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
  private String phone;
  private String email;
  private String password;
  private String role;
  private String status;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
}
