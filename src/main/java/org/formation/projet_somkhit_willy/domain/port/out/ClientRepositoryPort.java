package org.formation.projet_somkhit_willy.domain.port.out;

import org.formation.projet_somkhit_willy.domain.model.Client;

import java.util.List;
import java.util.Optional;

/**
 * Port sortant - abstraction de repository pour le domaine
 */
public interface ClientRepositoryPort {
    Client save(Client client);
    Optional<Client> findById(Long id);
    List<Client> findAll();
}
