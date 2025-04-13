package com.school.infrastructure.jpa.enums;

import lombok.Getter;

@Getter
public enum UserRoles {
  ADMIN("admin"),
  DOCTOR("doctor"),
  PATIENT("patient"),
  STAFF("staff");

  private String value;

  UserRoles(String value) {
    this.value = value;
  }
}
