package com.school.infrastructure.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prueba")
public class pruebacontroller {

  @RequestMapping("/saludos")
  public String saludos() {
    return "Hola mundo";
  }
}
