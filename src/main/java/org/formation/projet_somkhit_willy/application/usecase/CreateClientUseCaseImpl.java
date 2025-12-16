package org.formation.projet_somkhit_willy.application.usecase;

import org.formation.projet_somkhit_willy.domain.model.Client;
import org.formation.projet_somkhit_willy.domain.port.in.CreateClientUseCase;
import org.formation.projet_somkhit_willy.domain.port.out.ClientRepositoryPort;

/**
 * Implémentation du use case CreateClient - couche application/domain logic
 * Note: This class is framework-agnostic (no Spring annotations).
 */
public class CreateClientUseCaseImpl implements CreateClientUseCase {

    private final ClientRepositoryPort clientRepository;

    public CreateClientUseCaseImpl(ClientRepositoryPort clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(Client client) {
        // Business validations can happen here
        if (client.getFirstName() == null || client.getLastName() == null) {
            throw new IllegalArgumentException("firstName and lastName are required");
        }
        return clientRepository.save(client);
    }
}
