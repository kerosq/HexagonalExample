package com.school.infrastructure.jpa.enums;

import lombok.Getter;

@Getter
public enum UserStatus {
  ACTIVE("active"),
  INACTIVE("inactive"),
  SUSPENDED("suspended"),
  PENDING("pendig");

  private String value;

  UserStatus(String value) {
    this.value = value;
  }
}
