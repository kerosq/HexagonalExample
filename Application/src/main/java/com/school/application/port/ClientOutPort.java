package com.school.application.port;

import com.school.domain.model.Client;
import java.util.List;

// * Port secondary or output for the entity Client

public interface ClientOutPort {
  Client create(Client client);

  List<Client> findAll();
}
