package com.school.domain.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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

  // Añadir lista de horarios. Inicializarla ayuda a evitar NullPointerException.
  @Builder.Default private List<DoctorSchedule> schedules = new ArrayList<>();

  // Nota: Evitamos incluir colecciones (Appointments, Schedules, etc.) aquí
  // para mantener el modelo de dominio enfocado. Se pueden cargar bajo demanda si es necesario.
}
