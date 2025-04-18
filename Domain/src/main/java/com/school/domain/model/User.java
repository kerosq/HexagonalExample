package com.school.domain.model;

import com.school.domain.enums.UserRoles;
import com.school.domain.enums.UserStatus;
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
  private UserRoles role;
  private UserStatus status;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
}
