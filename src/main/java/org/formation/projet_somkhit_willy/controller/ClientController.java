package org.formation.projet_somkhit_willy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.formation.projet_somkhit_willy.entity.Client;
import org.formation.projet_somkhit_willy.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Client Management", description = "Endpoints for managing clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Create a new client.
     *
     * @param client the client to create
     * @return the created client
     */
    @Operation(summary = "Create a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid client data")
    })
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    /**
     * Get all clients.
     *
     * @return list of clients
     */
    @Operation(summary = "Get all clients")
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    /**
     * Get a client by ID.
     *
     * @param id the ID of the client
     * @return the client if found, 404 otherwise
     */
    @Operation(summary = "Get a client by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Update an existing client.
     *
     * @param id     the ID of the client to update
     * @param client the client data to update
     * @return the updated client
     */
    @Operation(summary = "Update a client by ID")
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }

    /**
     * Delete a client by ID.
     *
     * @param id the ID of the client to delete
     */
    @Operation(summary = "Delete a client by ID")
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
