package com.school.infrastructure.jpa.mapper;

import com.school.domain.model.Doctor;
import com.school.domain.util.mapper.MapperHelper;
import com.school.infrastructure.jpa.entity.DoctorEntity;
import com.school.infrastructure.jpa.entity.SpecialtyEntity; // Necesario si mapeas specialtyName
import com.school.infrastructure.jpa.entity.UserEntity; // Necesario para mapear userId
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/*
puedes usar implementationName si quieres un nombre específico
@Mapper(componentModel = "spring" , implementationName = "TuNombreDeImplementacion")
*/
@Mapper(componentModel = "spring")
public interface DoctorEntityMapper extends MapperHelper {

  @Mapping(source = "user", target = "userId", qualifiedByName = "userEntityToUserId")
  @Mapping(source = "specialty", target = "specialtyId", qualifiedByName = "specialtyEntityToId")
  Doctor mapToDomain(DoctorEntity doctorEntity);

  List<Doctor> mapToListDomain(List<DoctorEntity> doctorEntities);

  @Named("userEntityToUserId")
  default Long userEntityToUserId(UserEntity userEntity) {
    return userEntity != null ? userEntity.getId().longValue() : null;
  }

  @Named("specialtyEntityToId")
  default Integer specialtyEntityToId(SpecialtyEntity specialtyEntity) {
    return specialtyEntity != null ? specialtyEntity.getId() : null;
  }

  // No necesitas mapeo inverso (mapToEntity) para este ejemplo de lectura,
  // pero lo añadirías si implementaras creación/actualización.
}
