package com.example.demo.resources.impl;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.enums.ClientStatus;
import com.example.demo.domain.enums.ErrorType;
import com.example.demo.domain.exceptions.InvalidBalanceException;
import com.example.demo.domain.service.ClientService;
import com.example.demo.domain.service.ValidationService;
import com.example.demo.resources.dao.ClientDao;
import com.example.demo.resources.database.ClientRepository;
import com.example.demo.web.dto.request.DepositRequest;
import com.example.demo.web.dto.request.UpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientGatewayImplTest {

    @InjectMocks
    private ClientGatewayImpl clientGateway;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ValidationService validationService;

    @Mock
    private ClientService clientService;

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
    }

    @Test
    @DisplayName("Should soft delete client success")
    void shouldSoftDeleteClient() {

        Client client = new Client();
        client.setClientId(1L);
        client.setStatus(ClientStatus.ACTIVE);

        ClientDao dao = new ClientDao();
        dao.setClientId(1L);
        dao.setStatus(ClientStatus.DESACTIVED);

        when(clientRepository.save(any(ClientDao.class)))
                .thenReturn(dao);

        Client result = clientGateway.deletClient(client);

        assertNotNull(result);
        assertEquals(ClientStatus.DESACTIVED, result.getStatus());

        ArgumentCaptor<ClientDao> captor = ArgumentCaptor.forClass(ClientDao.class);
        verify(clientRepository).save(captor.capture());

        ClientDao saved = captor.getValue();
        assertEquals(ClientStatus.DESACTIVED, saved.getStatus());
    }

    @Test
    @DisplayName("Should deposit amount successfully")
    void DepositSuccessfully() {

        Client client = new Client();
        client.setClientId(1L);
        client.setBalance(new BigDecimal("100.00"));

        DepositRequest request = new DepositRequest();
        request.setAmount(new BigDecimal("50.00"));

        ClientDao dao = new ClientDao();
        dao.setClientId(1L);

        when(clientRepository.save(any(ClientDao.class))).thenReturn(dao);

        Client result = clientGateway.depositById(client, request);

        assertNotNull(result);
        assertEquals(new BigDecimal("150.00"), result.getBalance());

        verify(clientRepository).save(any(ClientDao.class));
    }
}
