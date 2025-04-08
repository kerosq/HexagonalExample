package com.school.infrastructure.rest.mapper;

import com.school.domain.model.User;
import com.school.domain.util.mapper.MapperHelper;
import com.school.infrastructure.rest.dto.UserDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRestMapper extends MapperHelper {

  List<UserDto> mapList(List<User> users);
}
