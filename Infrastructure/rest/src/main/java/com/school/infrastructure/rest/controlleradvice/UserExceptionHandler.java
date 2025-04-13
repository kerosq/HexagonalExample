package com.school.infrastructure.rest.controlleradvice;

import com.school.domain.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

  @ExceptionHandler(UserException.class)
  public ResponseEntity<ErrorResponse> handleUserException(UserException userException) {
    HttpStatus httpStatus = HttpStatus.valueOf(userException.getErrorType().getHttpStatusCode());

    ErrorResponse errorResponse =
        new ErrorResponse(
            userException.getMessage(),
            userException.getErrorType().getHttpStatusCode(),
            userException.getMetaData());

    return new ResponseEntity<>(errorResponse, httpStatus);
  }
}
