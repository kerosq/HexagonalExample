package com.school.infrastructure.rest.controller;

import com.school.domain.usecase.GetUsersUseCase;
import com.school.infrastructure.rest.api.UserApi;
import com.school.infrastructure.rest.mapper.UserRestMapper;
import com.school.infrastructure.rest.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUsersDelegate implements UserApi {
  private final UserRestMapper userRestMapper;
  private final GetUsersUseCase getUsersUseCase;

  @Override
  public ResponseEntity<User> _getUserById(Long userId) {
    return null;
  }

  @Override
  public ResponseEntity<List<User>> _getUsers() {
    return null;
  }
}
