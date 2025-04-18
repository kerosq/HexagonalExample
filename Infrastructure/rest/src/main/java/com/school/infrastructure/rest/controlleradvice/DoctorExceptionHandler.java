package com.school.infrastructure.rest.controlleradvice;

import com.school.domain.exception.DoctorException;
import com.school.infrastructure.rest.controlleradvice.template.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DoctorExceptionHandler {

  @ExceptionHandler(DoctorException.class)
  public ResponseEntity<ErrorResponse> handleDoctorException(DoctorException doctorException) {
    HttpStatus httpStatus = HttpStatus.valueOf(doctorException.getErrorType().getHttpStatusCode());

    ErrorResponse errorResponse =
        new ErrorResponse(
            doctorException.getMessage(),
            doctorException.getErrorType().getHttpStatusCode(),
            doctorException.getMetaData());

    return new ResponseEntity<>(errorResponse, httpStatus);
  }
}
