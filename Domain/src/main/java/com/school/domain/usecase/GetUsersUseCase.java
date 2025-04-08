package com.school.domain.usecase;

import com.school.domain.model.User;
import java.util.List;

@FunctionalInterface
public interface GetUsersUseCase {
  List<User> getUsers();
}
