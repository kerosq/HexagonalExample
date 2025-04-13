package com.school.infrastructure.rest.controller;

import com.school.domain.usecase.GetUserByIdUseCase;
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
  private final GetUserByIdUseCase getUserByIdUseCase;

  @Override
  public ResponseEntity<List<UserDto>> getUsers() {

    log.info("getUsers");
    final var domainUsers = getUsersUseCase.getUsers();
    final var userDtoList = userRestMapper.mapListToDto(domainUsers);

    return ResponseEntity.ok(userDtoList);
  }

  @Override
  public ResponseEntity<UserDto> getUserById(final Long userId) {

    log.info("getUserById by userId {}", userId);
    final var domainUser = getUserByIdUseCase.getUserById(userId);
    final var userDto = userRestMapper.mapToDto(domainUser);

    return ResponseEntity.ok(userDto);
  }
}
