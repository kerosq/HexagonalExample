package com.school.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserExceptionErrorType {
  USER_NOT_FOUND("Usuario no encontrado", 400),
  USER_ALREADY_EXISTS("El usuario ya existe", 404),
  INVALID_USER_DATA("Datos de usuario inválidos", 403);

  private final String message;
  private final int httpStatusCode;
}
