package com.school.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserExceptionErrorType {
  USER_NOT_FOUND("Usuario no encontrado"),
  USER_ALREADY_EXISTS("El usuario ya existe"),
  INVALID_USER_DATA("Datos de usuario inválidos");

  private final String message;
}
