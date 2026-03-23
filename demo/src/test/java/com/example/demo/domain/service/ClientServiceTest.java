package com.example.demo.domain.service;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.enums.ClientStatus;
import com.example.demo.domain.exceptions.DomainException;
import com.example.demo.domain.exceptions.NotFoundException;
import com.example.demo.domain.gateway.ClientGateway;
import com.example.demo.web.dto.request.UpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientGateway clientGateway;

    @Mock
    private ValidationService validationService;

    @Test
    @DisplayName("Should return client when id exists.")
    void searchValidId() {

        Client client = new Client();
        when(clientGateway.getClientById(1L)).thenReturn(client);

        Client result = clientService.getClientById(1L);

        assertNotNull(result);
        verify(clientGateway).getClientById(1L);
    }

    @Test
    @DisplayName("Should throw exception when client not found.")
    void searchNullId() {

        when(clientGateway.getClientById(1L)).thenReturn(null);

        assertThrows(NotFoundException.class,
                () -> clientService.getClientById(1L));

        verify(clientGateway).getClientById(1L);
    }

    @Test
    @DisplayName("Should return create client Success.")
    void createClient(){
        Client client = new Client();
        client.setAccount("13ABC");
        client.setName("Testing Create Client");
        client.setCpf("12345678910");
        client.setBalance(BigDecimal.valueOf(1500));
        client.setStatus(ClientStatus.ACTIVE);

        when(clientGateway.createClient(any(Client.class)))
                .thenReturn(client);

        Client result = clientService.createClient(client);

        assertNotNull(result);
        verify(clientGateway).createClient(any(Client.class));
    }

    @Test
    @DisplayName("Should return updated client Success.")
    void updateClient(){
        Long id = 1L;
    @DisplayName("Should logically delete client with id 2")
    void shouldDeleteClientLogically() throws DomainException {

        Long id = 2L;

        Client client = new Client();
        client.setClientId(id);

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setName("João");
        updateRequest.setCpf("12345678900");
        updateRequest.setStatus(ClientStatus.ACTIVE);

        when(clientGateway.getClientById(id)).thenReturn(client);
        when(clientGateway.updateClient(client, updateRequest)).thenReturn(client);

        Client result = clientService.updateClient(id, updateRequest);
        when(clientGateway.getClientById(id)).thenReturn(client);
        when(clientGateway.deletClient(client)).thenReturn(client);

        Client result = clientService.deletClient(id);

        assertNotNull(result);
        assertEquals(id, result.getClientId());

        verify(validationService).validationClientName(updateRequest.getName());
        verify(validationService).validationCpf(updateRequest.getCpf());
        verify(validationService).validationStatus((ClientStatus) updateRequest.getStatus());
        verify(clientGateway).updateClient(client, updateRequest);
        verify(clientGateway).getClientById(id);
        verify(clientGateway).deletClient(client);
    }
}
