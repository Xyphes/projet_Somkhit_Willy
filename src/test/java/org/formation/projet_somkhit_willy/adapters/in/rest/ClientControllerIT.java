package org.formation.projet_somkhit_willy.adapters.in.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.formation.projet_somkhit_willy.adapters.out.persistence.ClientJpaRepository;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerIT {

    @Autowired
    MockMvc mvc;

    @Autowired
    ClientJpaRepository clientJpaRepository;

    @Test
    void createClient_persists_and_returns201() throws Exception {
        String body = "{\"firstName\":\"John\",\"lastName\":\"Doe\"}";

        mvc.perform(post("/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id", is(notNullValue())));

        assertFalse(clientJpaRepository.findAll().isEmpty());
    }
}
