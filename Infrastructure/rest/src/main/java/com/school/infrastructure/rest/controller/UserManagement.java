package com.school.infrastructure.rest.controller;

import com.school.domain.usecase.GetUsersUseCase;
import com.school.infrastructure.rest.api.UserApi;
import com.school.infrastructure.rest.dto.UserDto;
import com.school.infrastructure.rest.mapper.UserRestMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserManagement implements UserApi {
  private final UserRestMapper userRestMapper;
  private final GetUsersUseCase getUsersUseCase;

  @Override
  public ResponseEntity<List<UserDto>> getUsers() {
    // Implementación

    return null;
  }
}
