package com.school.application.port;

import com.school.domain.model.Client;
import java.util.List;

// * Port primary or input for the entity Client
public interface ClientIntPort {
  Client create(Client client);

  List<Client> findAll();
}
