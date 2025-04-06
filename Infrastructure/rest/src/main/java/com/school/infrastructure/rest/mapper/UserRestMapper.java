package com.school.infrastructure.rest.mapper;

import com.school.domain.util.mapper.MapperHelper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRestMapper extends MapperHelper {}
