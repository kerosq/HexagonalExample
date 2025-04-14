package com.school.application.usecase.doctor;

import com.school.domain.exception.DoctorException; // Importa tu excepción
import com.school.domain.exception.DoctorExceptionErrorType; // Importa tus tipos de error
import com.school.domain.model.Doctor;
import com.school.domain.service.DoctorService;
import com.school.domain.usecase.GetDoctorByIdUseCase;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetDoctorById implements GetDoctorByIdUseCase {

  private final DoctorService doctorService;

  @Override
  public Doctor getDoctorById(Long doctorId) {

    final var doctor = doctorService.getDoctorById(doctorId);
    if (doctor == null) {
      log.error("User with ID {} not found", doctorId);
      Map<String, Object> metaData = Map.of("doctorId", doctorId.toString());
      throw new DoctorException(
          DoctorExceptionErrorType.DOCTOR_NOT_FOUND, metaData, doctorId.toString());
    }

    return doctor;
  }
}
