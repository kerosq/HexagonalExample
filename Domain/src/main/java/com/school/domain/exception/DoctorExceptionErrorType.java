package com.school.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DoctorExceptionErrorType {
  DOCTOR_NOT_FOUND("Doctor no encontrado", 404),
  INVALID_DOCTOR_DATA("Datos de doctor inválidos", 400);
  // Añadir más tipos según sea necesario

  private final String message;
  private final int httpStatusCode;
}
