package com.school.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {
  ADMIN("ADMIN"),
  TEACHER("TEACHER"),
  STUDENT("STUDENT");

  private final String type;
}
