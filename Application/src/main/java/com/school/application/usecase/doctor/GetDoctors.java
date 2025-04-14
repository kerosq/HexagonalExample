package com.school.application.usecase.doctor;

import com.school.domain.exception.DoctorException;
import com.school.domain.exception.DoctorExceptionErrorType;
import com.school.domain.model.Doctor;
import com.school.domain.service.DoctorService;
import com.school.domain.usecase.GetDoctorsUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetDoctors implements GetDoctorsUseCase {

  private final DoctorService doctorService;

  @Override
  public List<Doctor> getDoctors() {
    log.info("Executing GetDoctors use case");
    final var doctors = doctorService.getAllDoctors();

    // Verifica si la lista de doctores está vacía o nula y lanza una excepción
    if (doctors == null || doctors.isEmpty()) {
      log.warn("No doctors found");
      throw new DoctorException(DoctorExceptionErrorType.DOCTOR_NOT_FOUND, null, null);
    }
    return doctors;
  }
}
