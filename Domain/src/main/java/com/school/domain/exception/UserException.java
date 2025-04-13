package com.school.domain.exception;

import java.util.Map;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

  private final UserExceptionErrorType errorType;
  private final Map<String, Object> metaData;

  public UserException(
      UserExceptionErrorType errorType, Map<String, Object> metaData, String... args) {
    super(String.format(errorType.getMessage(), args));
    this.errorType = errorType;
    this.metaData = metaData;
  }
}
