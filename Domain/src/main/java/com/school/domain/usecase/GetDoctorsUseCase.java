package com.school.domain.usecase;

import com.school.domain.model.Doctor;

import java.util.List;

public interface GetDoctorsUseCase {
  List<Doctor> getDoctors();
}
