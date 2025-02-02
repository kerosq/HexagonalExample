package com.school.infrastructure.components.client.rest.dto;

import com.school.domain.model.Client;

import java.util.List;

public record ClientRes(Long id, String name, String lastName) {
    public static ClientRes toResponse(Client client) {
        return new ClientRes(client.id(), client.name(), client.lastName());
    }
    public static List<ClientRes> toResponse(List<Client> clients) {
        return clients.stream().map(ClientRes::toResponse).toList();
    }
}
