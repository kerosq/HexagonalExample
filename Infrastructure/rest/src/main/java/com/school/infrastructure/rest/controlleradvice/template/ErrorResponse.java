package com.school.infrastructure.rest.controlleradvice.template;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

  private String message;
  private int errorCode;
  private Map<String, Object> metaData;
}
