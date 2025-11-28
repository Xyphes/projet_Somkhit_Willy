package org.formation.projet_somkhit_willy;

import org.formation.projet_somkhit_willy.entity.Client;
import org.formation.projet_somkhit_willy.repository.ClientRepository;
import org.formation.projet_somkhit_willy.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {

    private ClientRepository clientRepository;
    private ClientServiceImpl clientService;

    @BeforeEach
    void setUp() {
        clientRepository = mock(ClientRepository.class);
        clientService = new ClientServiceImpl(clientRepository);
    }

    @Test
    void createClient_shouldReturnSavedClient() {
        Client client = new Client();
        client.setFirstName("John");
        client.setLastName("Doe");

        when(clientRepository.save(client)).thenReturn(client);

        Client result = clientService.createClient(client);
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void updateClient_existingClient_shouldUpdateAndReturnClient() {
        Long id = 1L;
        Client existingClient = new Client();
        existingClient.setId(id);
        existingClient.setFirstName("Jane");

        Client updatedClient = new Client();
        updatedClient.setFirstName("Janet");

        when(clientRepository.findById(id)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(existingClient)).thenReturn(existingClient);

        Client result = clientService.updateClient(id, updatedClient);
        assertNotNull(result);
        assertEquals("Janet", result.getFirstName());
        verify(clientRepository, times(1)).save(existingClient);
    }

    @Test
    void updateClient_nonExistingClient_shouldReturnNull() {
        Long id = 2L;
        Client updatedClient = new Client();

        when(clientRepository.findById(id)).thenReturn(Optional.empty());

        Client result = clientService.updateClient(id, updatedClient);
        assertNull(result);
        verify(clientRepository, never()).save(any());
    }

    @Test
    void getClientById_existingClient_shouldReturnOptional() {
        Long id = 1L;
        Client client = new Client();
        client.setId(id);

        when(clientRepository.findById(id)).thenReturn(Optional.of(client));

        Optional<Client> result = clientService.getClientById(id);
        assertTrue(result.isPresent());
        assertEquals(client, result.get());
    }

    @Test
    void getAllClients_shouldReturnListOfClients() {
        Client client1 = new Client();
        Client client2 = new Client();

        when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));

        List<Client> result = clientService.getAllClients();
        assertEquals(2, result.size());
    }

    @Test
    void deleteClient_shouldCallRepositoryDelete() {
        Long id = 1L;

        doNothing().when(clientRepository).deleteById(id);

        clientService.deleteClient(id);
        verify(clientRepository, times(1)).deleteById(id);
    }
}
