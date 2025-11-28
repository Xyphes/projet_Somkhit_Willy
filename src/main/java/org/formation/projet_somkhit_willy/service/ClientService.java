package org.formation.projet_somkhit_willy.service;

import org.formation.projet_somkhit_willy.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client createClient(Client client);
    Client updateClient(Long id, Client client);
    Optional<Client> getClientById(Long id);
    List<Client> getAllClients();
    void deleteClient(Long id);
}

