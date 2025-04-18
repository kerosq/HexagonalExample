package com.school.infrastructure.rest.mapper;

import com.school.domain.model.User;
import com.school.domain.util.mapper.MapperHelper;
import com.school.infrastructure.rest.dto.UserDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRestMapper extends MapperHelper {

  @Mapping(source = "id", target = "id")
  @Mapping(source = "name", target = "name")
  @Mapping(source = "address", target = "address")
  @Mapping(source = "age", target = "age")
  @Mapping(source = "phone", target = "phone")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "password", target = "password")
  @Mapping(source = "role", target = "role")
  @Mapping(source = "status", target = "status")
  UserDto mapToDto(User user);

  List<UserDto> mapListToDto(List<User> users);
}
