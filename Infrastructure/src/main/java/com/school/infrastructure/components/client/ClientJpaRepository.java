package com.school.infrastructure.components.client;

import com.school.infrastructure.components.client.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientJpaRepository extends JpaRepository<ClientEntity, Long> {}
