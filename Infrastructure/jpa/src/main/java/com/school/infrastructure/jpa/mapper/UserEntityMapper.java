package com.school.infrastructure.jpa.mapper;

import com.school.domain.model.User;
import com.school.domain.util.mapper.MapperHelper;
import com.school.infrastructure.jpa.entity.UserEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEntityMapper extends MapperHelper {

  @Mapping(source = "id", target = "id")
  @Mapping(source = "name", target = "name")
  @Mapping(source = "address", target = "address")
  @Mapping(source = "age", target = "age")
  @Mapping(source = "phone", target = "phone")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "password", target = "password")
  @Mapping(source = "role", target = "role")
  @Mapping(source = "status", target = "status")
  User map(UserEntity userEntity);

  List<User> map(List<UserEntity> userEntity);
}
