package com.school.domain.exception;

import java.util.Map;

public class UserException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private final UserExceptionErrorType errorType;
  private final Map<String, Object> metadata;

  public UserException(
      UserExceptionErrorType errorType, Map<String, Object> metadata, String... args) {
    super(String.format(errorType.getMessage(), args));
    this.errorType = errorType;
    this.metadata = metadata;
  }

  public UserExceptionErrorType getUserErrorType() {
    return errorType;
  }

  public Map<String, Object> getMetadata() {
    return metadata;
  }

  /* *
   * Podrás usarla así:
   * Map<String, Object> metadata = Map.of("userId", "123");
   * throw new serException(ErrorType.USER_NOT_FOUND, metadata, "123");
   * */

}
