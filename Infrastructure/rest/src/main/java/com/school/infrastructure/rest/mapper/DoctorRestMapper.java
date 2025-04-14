package com.school.infrastructure.rest.mapper;

import com.school.domain.model.Doctor;
import com.school.domain.util.mapper.MapperHelper;
import com.school.infrastructure.rest.dto.DoctorDto; // Se asume que se genera desde openapi.yml
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping; // Necesitarás mapeos explícitos

@Mapper(componentModel = "spring")
public interface DoctorRestMapper extends MapperHelper {

  // Mapea los campos del modelo de dominio al DTO generado
  // Asegúrate de que los nombres coincidan con las propiedades definidas en openapi.yml
  @Mapping(source = "id", target = "id")
  @Mapping(source = "userId", target = "userId")
  @Mapping(source = "firstName", target = "firstName")
  @Mapping(source = "lastName", target = "lastName")
  @Mapping(source = "specialtyId", target = "specialtyId")
  @Mapping(source = "licenseNumber", target = "licenseNumber")
  @Mapping(source = "phone", target = "phone")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "hireDate", target = "hireDate")
  @Mapping(source = "active", target = "active")
  @Mapping(source = "createdAt", target = "createdAt")
  @Mapping(source = "updatedAt", target = "updatedAt")
  DoctorDto mapToDto(Doctor doctor);

  List<DoctorDto> mapListToDto(List<Doctor> doctors);

  // No necesitas mapeo inverso (mapToDomain) para GET,
  // pero lo añadirías para POST/PUT.
}
