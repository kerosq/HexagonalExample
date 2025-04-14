package com.school.infrastructure.rest.controller;

import com.school.domain.usecase.GetDoctorByIdUseCase;
import com.school.domain.usecase.GetDoctorsUseCase;
import com.school.infrastructure.rest.api.DoctorApiDelegate; // Interfaz generada
import com.school.infrastructure.rest.dto.DoctorDto; // DTO generado
import com.school.infrastructure.rest.mapper.DoctorRestMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service; // O @Component

@Slf4j
@Service // O @Component
@RequiredArgsConstructor
public class DoctorManagement implements DoctorApiDelegate { // Implementa la interfaz generada

  private final DoctorRestMapper doctorRestMapper;
  private final GetDoctorsUseCase getDoctorsUseCase;
  private final GetDoctorByIdUseCase getDoctorByIdUseCase;

  // Sobrescribe los métodos definidos en DoctorApiDelegate (generados desde operationId en
  // openapi.yml)

  @Override
  public ResponseEntity<List<DoctorDto>> getDoctors() {
    log.info("Received request for getDoctors");
    final var domainDoctors = getDoctorsUseCase.getDoctors();
    final var doctorDtoList = doctorRestMapper.mapListToDto(domainDoctors);
    log.info("Returning {} doctors", doctorDtoList.size());
    return ResponseEntity.ok(doctorDtoList);
  }

  @Override
  public ResponseEntity<DoctorDto> getDoctorById(
      final Long doctorId) { // El tipo debe coincidir con openapi.yml
    log.info("Received request for getDoctorById with doctorId: {}", doctorId);
    final var domainDoctor =
        getDoctorByIdUseCase.getDoctorById(doctorId); // El caso de uso maneja 'not found'
    final var doctorDto = doctorRestMapper.mapToDto(domainDoctor);
    log.info("Returning doctor with ID: {}", doctorId);
    return ResponseEntity.ok(doctorDto);
  }
}
