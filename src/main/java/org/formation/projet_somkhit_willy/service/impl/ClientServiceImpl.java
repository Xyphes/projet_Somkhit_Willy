package org.formation.projet_somkhit_willy.service.impl;

import org.formation.projet_somkhit_willy.entity.Client;
import org.formation.projet_somkhit_willy.repository.ClientRepository;
import org.formation.projet_somkhit_willy.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long id, Client client) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client existingClient = optionalClient.get();
            existingClient.setLastName(client.getLastName());
            existingClient.setFirstName(client.getFirstName());
            existingClient.setAddress(client.getAddress());
            existingClient.setCity(client.getCity());
            existingClient.setPostalCode(client.getPostalCode());
            existingClient.setPhoneNumber(client.getPhoneNumber());
            return clientRepository.save(existingClient);
        }
        return null;
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
