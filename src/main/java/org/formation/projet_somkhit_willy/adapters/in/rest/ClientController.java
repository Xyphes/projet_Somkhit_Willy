package org.formation.projet_somkhit_willy.adapters.in.rest;

import org.formation.projet_somkhit_willy.domain.model.Client;
import org.formation.projet_somkhit_willy.domain.port.in.CreateClientUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final CreateClientUseCase createClientUseCase;

    public ClientController(CreateClientUseCase createClientUseCase) {
        this.createClientUseCase = createClientUseCase;
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody ClientDto dto) {
        Client domain = new Client(null, dto.getFirstName(), dto.getLastName());
        Client saved = createClientUseCase.createClient(domain);
        ClientDto result = new ClientDto(saved.getId(), saved.getFirstName(), saved.getLastName());
        return ResponseEntity.created(URI.create("/api/clients/" + saved.getId())).body(result);
    }
}
