package org.formation.projet_somkhit_willy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAspectJAutoProxy
// Explicitly limit component scanning and JPA scanning to the new hexagonal packages
@ComponentScan(basePackages = {
    "org.formation.projet_somkhit_willy.adapters",
    "org.formation.projet_somkhit_willy.aspect"
})
@EnableJpaRepositories(basePackages = "org.formation.projet_somkhit_willy.adapters.out.persistence")
@EntityScan(basePackages = "org.formation.projet_somkhit_willy.adapters.out.persistence")
public class ProjetSomkhitWillyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetSomkhitWillyApplication.class, args);
    }

}
