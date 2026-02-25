package com.example.demo.resources.impl;

import com.example.demo.domain.entity.Client;
import com.example.demo.resources.dao.ClientDao;
import com.example.demo.resources.database.ClientRepository;
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
}
