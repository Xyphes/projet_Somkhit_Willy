package org.formation.projet_somkhit_willy.adapters.out.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.formation.projet_somkhit_willy.domain.model.Client;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientPersistenceAdapterTest {

    @Autowired
    ClientPersistenceAdapter adapter;

    @Autowired
    ClientJpaRepository jpaRepository;

    @Test
    void saveAndFindById() {
        Client domain = new Client(null, "Alice", "Smith");
        Client saved = adapter.save(domain);
        assertNotNull(saved.getId());

        Optional<Client> found = adapter.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Alice", found.get().getFirstName());
    }
}
