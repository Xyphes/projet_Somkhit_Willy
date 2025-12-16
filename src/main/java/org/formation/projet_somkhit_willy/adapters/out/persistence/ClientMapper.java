package org.formation.projet_somkhit_willy.adapters.out.persistence;

import org.formation.projet_somkhit_willy.domain.model.Client;

/**
 * Simple mapper between domain and JPA entity
 */
public final class ClientMapper {
    private ClientMapper() {}

    public static Client toDomain(ClientJpaEntity e) {
        if (e == null) return null;
        return new Client(e.getId(), e.getFirstName(), e.getLastName());
    }

    public static ClientJpaEntity toEntity(Client d) {
        if (d == null) return null;
        return new ClientJpaEntity(d.getId(), d.getFirstName(), d.getLastName());
    }
}
