package com.school.infrastructure.persistence.repository;

import com.school.infrastructure.persistence.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

  Optional<UserEntity> findUserEntityByUsername(String username);
}
