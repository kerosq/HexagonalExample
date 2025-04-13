package com.school.application.usecase.user;

import com.school.domain.exception.UserException;
import com.school.domain.exception.UserExceptionErrorType;
import com.school.domain.model.User;
import com.school.domain.service.UserService;
import com.school.domain.usecase.GetUserByIdUseCase;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetUserById implements GetUserByIdUseCase {
  private final UserService userService;

  @Override
  public User getUserById(Long userId) {

    final var user = userService.getUserById(userId);

    if (user == null) {
      log.error("User with ID {} not found", userId);
      Map<String, Object> metaData = Map.of("userId", userId.toString());
      throw new UserException(UserExceptionErrorType.USER_NOT_FOUND, metaData, userId.toString());
    }

    return user;
  }
}
