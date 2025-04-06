package com.school.infrastructure.rest.controller;

import com.school.domain.usecase.GetUsersUseCase;
import com.school.infrastructure.rest.mapper.UserRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUsersDelegate {
  private final UserRestMapper userRestMapper;
  private final GetUsersUseCase getUsersUseCase;
}
