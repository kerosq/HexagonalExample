package com.school.infrastructure.jpa.mapper;

import com.school.domain.model.DoctorSchedule;
import com.school.domain.util.mapper.MapperHelper;
import com.school.infrastructure.jpa.entity.DoctorScheduleEntity;
import java.util.List;
import java.util.Set; // La entidad usa Set
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorScheduleEntityMapper extends MapperHelper {

  DoctorSchedule mapToDomain(DoctorScheduleEntity entity);

  // MapStruct puede mapear de Set a List automáticamente
  List<DoctorSchedule> mapToDomainList(Set<DoctorScheduleEntity> entities);
}
