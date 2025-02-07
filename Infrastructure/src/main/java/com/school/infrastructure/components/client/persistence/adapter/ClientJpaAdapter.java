package com.school.infrastructure.components.client.persistence.adapter;

import com.school.application.port.ClientOutPort;
import com.school.domain.model.Client;
import com.school.infrastructure.components.client.ClientJpaRepository;
import com.school.infrastructure.components.client.persistence.mapper.ClientMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientJpaAdapter implements ClientOutPort {

  private final ClientJpaRepository repository;

  @Override
  public Client create(Client client) {
    return ClientMapper.toDomain(repository.save(ClientMapper.toEntity(client)));
  }

  @Override
  public List<Client> findAll() {
    return ClientMapper.toDomain(repository.findAll());
  }
}
