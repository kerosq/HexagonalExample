package com.school.domain.exception;

import java.util.Map;
import lombok.Getter;

@Getter
public class DoctorException extends RuntimeException {

  private final DoctorExceptionErrorType errorType;
  private final Map<String, Object> metaData;

  public DoctorException(
      DoctorExceptionErrorType errorType, Map<String, Object> metaData, String... args) {
    super(String.format(errorType.getMessage(), (Object[]) args));
    this.errorType = errorType;
    this.metaData = metaData;
  }
}
