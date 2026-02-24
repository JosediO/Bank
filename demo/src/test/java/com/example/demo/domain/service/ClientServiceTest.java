package com.example.demo.domain.service;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.exceptions.NotFoundException;
import com.example.demo.domain.gateway.ClientGateway;
import com.example.demo.resources.dao.ClientDao;
import com.example.demo.resources.database.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientGateway clientGateway;

    @Test
    void shouldReturnClientWhenIdExists() {

        Client client = new Client();
        when(clientGateway.getClientById(1L)).thenReturn(client);

        Client result = clientService.getClientById(1L);

        assertNotNull(result);
        verify(clientGateway).getClientById(1L);
    }

    @Test
    void shouldThrowExceptionWhenClientNotFound() {

        when(clientGateway.getClientById(1L)).thenReturn(null);

        assertThrows(NotFoundException.class,
                () -> clientService.getClientById(1L));

        verify(clientGateway).getClientById(1L);
    }
}
