package org.formation.projet_somkhit_willy.domain.port.in;

import org.formation.projet_somkhit_willy.domain.model.Client;

/**
 * Port entrant (use case) - interface définissant l'opération de création de Client
 */
public interface CreateClientUseCase {
    Client createClient(Client client);
}
