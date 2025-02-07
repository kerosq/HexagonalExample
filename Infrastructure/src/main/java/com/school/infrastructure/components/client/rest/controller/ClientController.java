package com.school.infrastructure.components.client.rest.controller;

import com.school.application.port.ClientIntPort;
import com.school.infrastructure.components.client.rest.dto.ClientCreateReq;
import com.school.infrastructure.components.client.rest.dto.ClientRes;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@PreAuthorize("denyAll()")
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

  @GetMapping("/list")
  @PreAuthorize("permitAll()")
  public List<ClientRes> getAll() {
    return ClientRes.toResponse(port.findAll());
  }

  @GetMapping("/secureclient")
  @PreAuthorize("hasAuthority('read')")
  public String getById() {
    return "hola";
  }

  @GetMapping("/nosecureclient")
  public String getById2() {
    return "hola";
  }
}
