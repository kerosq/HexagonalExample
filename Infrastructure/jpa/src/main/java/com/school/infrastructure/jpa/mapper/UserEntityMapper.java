package com.school.infrastructure.jpa.mapper;

import com.school.domain.model.User;
import com.school.domain.util.mapper.MapperHelper;
import com.school.infrastructure.jpa.entity.UserEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper extends MapperHelper {

  User map(UserEntity userEntity);

  List<User> map(List<UserEntity> userEntity);
}
