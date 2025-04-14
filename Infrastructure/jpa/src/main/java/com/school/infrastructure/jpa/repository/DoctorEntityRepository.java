package com.school.infrastructure.jpa.repository;

import com.school.infrastructure.jpa.entity.DoctorEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorEntityRepository extends ListCrudRepository<DoctorEntity, Long> {
  // Puedes añadir métodos de búsqueda personalizados aquí si los necesitas
  // Ejemplo: List<DoctorEntity> findByActiveTrue();
}
