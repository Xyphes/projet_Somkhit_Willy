package org.formation.projet_somkhit_willy.adapters.out.persistence;

import org.formation.projet_somkhit_willy.domain.model.Client;
import org.formation.projet_somkhit_willy.domain.port.out.ClientRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClientPersistenceAdapter implements ClientRepositoryPort {

    private final ClientJpaRepository jpaRepository;

    public ClientPersistenceAdapter(ClientJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Client save(Client client) {
        ClientJpaEntity entity = ClientMapper.toEntity(client);
        ClientJpaEntity saved = jpaRepository.save(entity);
        return ClientMapper.toDomain(saved);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return jpaRepository.findById(id).map(ClientMapper::toDomain);
    }

    @Override
    public List<Client> findAll() {
        return jpaRepository.findAll().stream().map(ClientMapper::toDomain).collect(Collectors.toList());
    }
}
