package com.school.infrastructure.rest.controller;

import com.school.domain.usecase.GetUsersUseCase;
import com.school.infrastructure.rest.api.UserApiDelegate;
import com.school.infrastructure.rest.dto.UserDto;
import com.school.infrastructure.rest.mapper.UserRestMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagement implements UserApiDelegate {
  private final UserRestMapper userRestMapper;
  private final GetUsersUseCase getUsersUseCase;

  @Override
  public ResponseEntity<List<UserDto>> getUsers() {
    // Implementación

    final var users = userRestMapper.mapList(getUsersUseCase.getUsers());

    return ResponseEntity.ok(users);
  }
}
