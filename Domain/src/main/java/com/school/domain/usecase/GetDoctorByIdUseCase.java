package com.school.domain.usecase;

import com.school.domain.model.Doctor;

public interface GetDoctorByIdUseCase {
  Doctor getDoctorById(Long doctorId); // Puede lanzar excepción si no se encuentra
}
