package com.school.infrastructure.jpa.repository;

import com.school.infrastructure.jpa.entity.UserEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends ListCrudRepository<UserEntity, Long> {}
