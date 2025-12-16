package org.formation.projet_somkhit_willy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContextControllerScanTest {

    @Autowired
    ApplicationContext ctx;

    @Test
    void only_hexagonal_client_controller_is_present() {
        String[] adapters = ctx.getBeanNamesForType(org.formation.projet_somkhit_willy.adapters.in.rest.ClientController.class);
        String[] legacy = ctx.getBeanNamesForType(org.formation.projet_somkhit_willy.controller.ClientController.class);

        assertEquals(1, adapters.length, "Expected hexagonal adapter controller to be present");
        assertEquals(0, legacy.length, "Legacy controller should NOT be registered");
    }
}
