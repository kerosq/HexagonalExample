package com.school.infrastructure.jpa.persistence;

import com.school.domain.model.Doctor;
import com.school.domain.service.DoctorService;
import com.school.infrastructure.jpa.mapper.DoctorEntityMapper;
import com.school.infrastructure.jpa.repository.DoctorEntityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service // También puedes definirlo como @Service
public class DoctorPersistenceService implements DoctorService {

  /*
  ¡Asegúrate que el nombre del mapper sea correcto y que esté en el contexto de Spring!
  */
  private final DoctorEntityMapper doctorEntityMapperMapper;

  private final DoctorEntityRepository doctorEntityRepository;

  public DoctorPersistenceService(
      @Qualifier("doctorEntityMapperImpl") DoctorEntityMapper doctorEntityMapperMapper,
      DoctorEntityRepository doctorEntityRepository) {
    this.doctorEntityMapperMapper = doctorEntityMapperMapper;
    this.doctorEntityRepository = doctorEntityRepository;
  }

  @Override
  public List<Doctor> getAllDoctors() {
    final var doctorEntities = doctorEntityRepository.findAll();
    return doctorEntityMapperMapper.mapToListDomain(doctorEntities);
  }

  @Override
  public Doctor getDoctorById(Long doctorId) {

    final var doctor = doctorEntityRepository.findById(doctorId).orElse(null);
    return doctorEntityMapperMapper.mapToDomain(doctor);
  }
}
