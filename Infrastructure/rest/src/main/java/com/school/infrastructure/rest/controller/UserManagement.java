package com.school.infrastructure.rest.controller;

import com.school.domain.usecase.GetUsersUseCase;
import com.school.infrastructure.rest.api.UserApiDelegate;
import com.school.infrastructure.rest.dto.UserDto;
import com.school.infrastructure.rest.mapper.UserRestMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserManagement implements UserApiDelegate {
  private final UserRestMapper userRestMapper;
  private final GetUsersUseCase getUsersUseCase;

  @Override
  public ResponseEntity<List<UserDto>> getUsers() {

    log.info("[APPLICATION-USECASE] - getUsers");
    final var domainUsers = getUsersUseCase.getUsers();
    final var userDtos = userRestMapper.mapListToDto(domainUsers);

    if (userDtos == null || userDtos.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(userDtos);
  }
}
