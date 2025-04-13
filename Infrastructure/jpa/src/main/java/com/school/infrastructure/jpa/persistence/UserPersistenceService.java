package com.school.infrastructure.jpa.persistence;

import com.school.domain.model.User;
import com.school.domain.service.UserService;
import com.school.infrastructure.jpa.mapper.UserEntityMapper;
import com.school.infrastructure.jpa.repository.UserEntityRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceService implements UserService {

  private final UserEntityMapper userEntityMapper;
  private final UserEntityRepository userEntityRepository;

  @Override
  public List<User> getUsers() {

    final var usersEntity = userEntityRepository.findAll();
    return userEntityMapper.mapToListDomain(usersEntity);
  }

  @Override
  public User getUserById(Long id) {

    final var userEntity = userEntityRepository.findById(id).orElse(null);
    return userEntityMapper.mapToDomain(userEntity);
  }
}
