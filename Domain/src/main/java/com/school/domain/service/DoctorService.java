package com.school.domain.service;

import com.school.domain.model.Doctor;
import java.util.List;

public interface DoctorService {

  List<Doctor> getAllDoctors();

  Doctor getDoctorById(Long doctorId);
  // Aquí irían otros métodos CRUD si los necesitas (save, update, delete)
}
