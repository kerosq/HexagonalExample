package com.school.infrastructure.rest.mapper;

import com.school.domain.model.DoctorSchedule;
import com.school.domain.util.mapper.MapperHelper;
import com.school.infrastructure.rest.dto.DoctorScheduleDto; // DTO Generado
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorScheduleRestMapper extends MapperHelper {
  DoctorScheduleDto mapToDto(DoctorSchedule domain);

  List<DoctorScheduleDto> mapToDtoList(List<DoctorSchedule> domainList);
}
