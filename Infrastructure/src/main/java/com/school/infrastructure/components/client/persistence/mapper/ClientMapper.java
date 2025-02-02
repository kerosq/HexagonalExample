package com.school.infrastructure.components.client.persistence.mapper;

import com.school.domain.model.Client;
import com.school.infrastructure.components.client.persistence.entity.ClientEntity;

import java.util.List;

public class ClientMapper {

    private ClientMapper() {}
    public static Client toDomain(ClientEntity entity) {
        return new Client(entity.getId(), entity.getName(), entity.getLastName());
    }
    public static List<Client> toDomain(List<ClientEntity> entityList) {
        return entityList.stream().map(ClientMapper::toDomain).toList();
    }
    public static ClientEntity toEntity(Client client) {
        return new ClientEntity(client.id(), client.name(), client.lastName());
    }
}