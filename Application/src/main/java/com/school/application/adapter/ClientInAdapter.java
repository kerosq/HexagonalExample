package com.school.application.adapter;

import com.school.application.port.ClientIntPort;
import com.school.application.port.ClientOutPort;
import com.school.domain.model.Client;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Singleton // * Designates that the class will be singleton in circle of life of the application on Jakarta EE
@AllArgsConstructor
// * Adapter for the input port of the entity Client USE CASE
public class ClientInAdapter implements ClientIntPort {
    private final ClientOutPort clientOutPort;

    @Override
    public Client create(Client client) {
        return clientOutPort.create(client);
    }

    @Override
    public List<Client> findAll() {
        return clientOutPort.findAll();
    }
}
