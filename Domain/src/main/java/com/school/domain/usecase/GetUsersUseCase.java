package com.school.domain.usecase;

import com.school.domain.model.User;
import java.util.List;

public interface GetUsersUseCase {
  List<User> getUsers();
}
