package com.school.domain.usecase;

import com.school.domain.model.User;

public interface GetUserByIdUseCase {
  User getUserById(Long userId);
}
