package org.formation.projet_somkhit_willy.service;

import org.formation.projet_somkhit_willy.entity.Client;

import java.util.List;

public interface ClientService {
    Client createClient(Client client);
    Client updateClient(Long id, Client client);
    Client getClientById(Long id);
    List<Client> getAllClients();
    void deleteClient(Long id);
}

