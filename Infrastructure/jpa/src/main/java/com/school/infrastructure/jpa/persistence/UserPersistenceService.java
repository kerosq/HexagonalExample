package com.school.infrastructure.jpa.persistence;

import com.school.domain.model.User;
import com.school.domain.service.UserService;
import com.school.infrastructure.jpa.mapper.UserEntityMapper;
import com.school.infrastructure.jpa.repository.UserEntityRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserPersistenceService implements UserService {

  /*
  ¡Asegúrate que el nombre del mapper sea correcto y que esté en el contexto de Spring!
  */
  private final UserEntityMapper userEntityMapper;
  private final UserEntityRepository userEntityRepository;

  public UserPersistenceService(
      @Qualifier("userEntityMapperImpl") UserEntityMapper userEntityMapper,
      UserEntityRepository userEntityRepository) {
    this.userEntityMapper = userEntityMapper;
    this.userEntityRepository = userEntityRepository;
  }

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
