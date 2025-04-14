package com.school.domain.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

  private Long id;
  private Long userId;
  private String firstName;
  private String lastName;
  private Integer specialtyId;
  private String licenseNumber;
  private String phone;
  private String email;
  private LocalDate hireDate;
  private Boolean active;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  // Nota: Evitamos incluir colecciones (Appointments, Schedules, etc.) aquí
  // para mantener el modelo de dominio enfocado. Se pueden cargar bajo demanda si es necesario.
}
