package com.school.infrastructure.components.client.rest.dto;

import com.school.domain.model.Client;

public record ClientCreateReq(String name, String lastName) {
  public static Client toDomain(ClientCreateReq client) {
    return new Client(null, client.name, client.lastName);
  }
}
