package com.example.demo.resources.impl;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.enums.ClientStatus;
import com.example.demo.resources.dao.ClientDao;
import com.example.demo.resources.database.ClientRepository;
import com.example.demo.web.dto.request.UpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ClientGatewayImplTest {

    @InjectMocks
    private ClientGatewayImpl clientGateway;

    @Mock
    private ClientRepository clientRepository;

    @Test
    @DisplayName("given a valid id, the searching is successfully")
    void shouldReturnClientWhenIdExists() {

        Long id = 1L;

        ClientDao dao = new ClientDao();
        dao.setClientId(id);
        dao.setAccount("A1234");
        dao.setName("John Doe");
        dao.setCpf("12345678901");

        when(clientRepository.findById(id))
                .thenReturn(Optional.of(dao));

        Client result = clientGateway.getClientById(id);

        assertNotNull(result);
        assertEquals(id, result.getClientId());
        assertEquals("John Doe", result.getName());

        verify(clientRepository).findById(id);
    }

    @Test
    @DisplayName("given a valid client, should create successfully")
    void shouldCreateClient() {

        Client input = new Client();
        input.setClientId(1L);
        input.setAccount("A1234");
        input.setName("John Doe");

        ClientDao savedDao = new ClientDao();
        savedDao.setClientId(1L);
        savedDao.setAccount("A1234");
        savedDao.setName("John Doe");

        when(clientRepository.save(any(ClientDao.class)))
                .thenReturn(savedDao);

        Client result = clientGateway.createClient(input);

        assertNotNull(result);
        assertEquals(1L, result.getClientId());
        assertEquals("John Doe", result.getName());

        verify(clientRepository).save(any(ClientDao.class));
    }

    @Test
    @DisplayName("Should update client successfully")
    void shouldUpdateClient() {

        Client client = new Client();
        client.setClientId(1L);
        client.setName("Old Name");
        client.setCpf("11111111111");
        client.setStatus(ClientStatus.ACTIVE);

        UpdateRequest request = new UpdateRequest();
        request.setName("New Name");
        request.setCpf("22222222222");
        request.setStatus(ClientStatus.DESACTIVED);

        ClientDao dao = new ClientDao();
        dao.setClientId(1L);

        when(clientRepository.save(any(ClientDao.class))).thenReturn(dao);

        Client result = clientGateway.updateClient(client, request);

        assertNotNull(result);
        assertEquals("New Name", result.getName());
        assertEquals("22222222222", result.getCpf());
        assertEquals(ClientStatus.DESACTIVED, result.getStatus());

        verify(clientRepository).save(any(ClientDao.class));
    }

}
