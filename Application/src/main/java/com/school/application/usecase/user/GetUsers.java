package com.school.application.usecase.user;

import com.school.domain.model.User;
import com.school.domain.service.UserService;
import com.school.domain.usecase.GetUsersUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetUsers implements GetUsersUseCase {

  private final UserService userService;

  @Override
  public List<User> getUsers() {
    log.info("[APPLICATION-USECASE] - getUsers");

    return userService.getUsers();
  }
}
