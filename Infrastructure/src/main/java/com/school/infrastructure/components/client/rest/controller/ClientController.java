package com.school.infrastructure.components.client.rest.controller;

import com.school.application.port.ClientIntPort;
import com.school.infrastructure.components.client.rest.dto.ClientCreateReq;
import com.school.infrastructure.components.client.rest.dto.ClientRes;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
public class ClientController {
  private final ClientIntPort port;

  public ClientController(ClientIntPort port) {
    this.port = port;
  }

  @PostMapping()
  public ResponseEntity<ClientRes> create(@RequestBody ClientCreateReq clientToCreate) {
    var client = port.create(ClientCreateReq.toDomain(clientToCreate));
    return ResponseEntity.status(HttpStatus.OK).body(ClientRes.toResponse(client));
  }

  @GetMapping()
  public List<ClientRes> getAll() {
    return ClientRes.toResponse(port.findAll());
  }
}
