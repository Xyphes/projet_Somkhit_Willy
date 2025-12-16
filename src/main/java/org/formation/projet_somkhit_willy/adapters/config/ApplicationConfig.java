package org.formation.projet_somkhit_willy.adapters.config;

import org.formation.projet_somkhit_willy.application.usecase.CreateClientUseCaseImpl;
import org.formation.projet_somkhit_willy.domain.port.in.CreateClientUseCase;
import org.formation.projet_somkhit_willy.domain.port.out.ClientRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration des adaptateurs pour exposer les use cases (beans) sans annoter
 * les classes d'application/domain avec Spring.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public CreateClientUseCase createClientUseCase(ClientRepositoryPort clientRepository) {
        return new CreateClientUseCaseImpl(clientRepository);
    }
}
