package com.example.demo.domain.service;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.enums.ClientStatus;
import com.example.demo.domain.enums.ErrorType;
import com.example.demo.domain.exceptions.DomainException;
import com.example.demo.domain.exceptions.InvalidBalanceException;
import com.example.demo.domain.exceptions.NotFoundException;
import com.example.demo.domain.gateway.ClientGateway;
import com.example.demo.resources.dao.ClientDao;
import com.example.demo.resources.database.ClientRepository;
import com.example.demo.web.dto.request.DepositRequest;
import com.example.demo.web.dto.request.UpdateRequest;
import com.example.demo.web.dto.request.WithdrawRequest;
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
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

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
    void updateClient() {
        Long id = 1L;

        Client client = new Client();
        client.setClientId(id);

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setName("João");
        updateRequest.setCpf("12345678900");
        updateRequest.setStatus(ClientStatus.ACTIVE);

        when(clientGateway.getClientById(id)).thenReturn(client);
        when(clientGateway.updateClient(client, updateRequest)).thenReturn(client);

        Client result = clientService.updateClient(id, updateRequest);

        assertNotNull(result);
        assertEquals(id, result.getClientId());

        verify(validationService).validationClientName(updateRequest.getName());
        verify(validationService).validationCpf(updateRequest.getCpf());
        verify(validationService).validationStatus((ClientStatus) updateRequest.getStatus());
        verify(clientGateway).updateClient(client, updateRequest);

    }

    @Test
    @DisplayName("Should soft delete client success")
    void shouldSoftDeleteClient(){

        Long id = 1L;

        Client client = new Client();
        client.setClientId(id);
        client.setStatus(ClientStatus.ACTIVE);

        when(clientGateway.getClientById(id)).thenReturn(client);

        client.setStatus(ClientStatus.DESACTIVED);
        when(clientGateway.deletClient(client)).thenReturn(client);

        Client result = clientService.deletClient(id);

        assertNotNull(result);
        assertEquals(ClientStatus.DESACTIVED, result.getStatus());

        verify(clientGateway).getClientById(id);
        verify(clientGateway).deletClient(client);
    }

    @Test
    @DisplayName("Should deposit successfully")
    void DepositSuccess() {

        Long id = 1L;

        Client client = new Client();
        client.setClientId(id);

        DepositRequest request = new DepositRequest();
        request.setAmount(new BigDecimal("100.00"));

        when(clientGateway.getClientById(id)).thenReturn(client);
        when(clientGateway.depositById(client, request)).thenReturn(client);

        Client result = clientService.depositById(id, request);

        assertNotNull(result);
        assertEquals(id, result.getClientId());

        verify(validationService).validationPositiveBalance(request.getAmount());
        verify(clientGateway).depositById(client, request);
    }

    @Test
    @DisplayName("Should throw exception when deposit is negative")
    void DepositIsNegative() {

        Long id = 1L;

        Client client = new Client();
        client.setClientId(id);

        DepositRequest request = new DepositRequest();
        request.setAmount(new BigDecimal("-50.00"));

        when(clientGateway.getClientById(id)).thenReturn(client);

        doThrow(new InvalidBalanceException("The balance is negative!", ErrorType.INVALID_VALUE))
                .when(validationService)
                .validationPositiveBalance(request.getAmount());

        assertThrows(InvalidBalanceException.class, () ->
                clientService.depositById(id, request)
        );

        verify(clientGateway).getClientById(id);
        verify(validationService).validationPositiveBalance(request.getAmount());
        verify(clientGateway, never()).depositById(any(), any());
    }

    @Test
    @DisplayName("Should throw exception when deposit amount is null")
    void ExceptionWhenAmountIsNull() {

        Long id = 1L;

        Client client = new Client();
        client.setClientId(id);

        DepositRequest request = new DepositRequest();
        request.setAmount(null);

        when(clientGateway.getClientById(id)).thenReturn(client);

        doThrow(new InvalidBalanceException("Balance cannot be null", ErrorType.INVALID_VALUE))
                .when(validationService)
                .validationPositiveBalance(null);

        assertThrows(InvalidBalanceException.class, () ->
                clientService.depositById(id, request)
        );
        verify(clientGateway).getClientById(id);
        verify(validationService).validationPositiveBalance(null);
        verify(clientGateway, never()).depositById(any(), any());
    }

    @Test
    @DisplayName("Should withdraw successfully")
    void WithdrawSuccess() {

        Long id = 1L;

        Client client = new Client();
        client.setClientId(id);
        client.setBalance(new BigDecimal("100.00"));

        WithdrawRequest request = new WithdrawRequest();
        request.setAmount(new BigDecimal("50.00"));

        // mocks
        when(clientGateway.getClientById(id)).thenReturn(client);
        when(clientGateway.withdrawById(client, request)).thenReturn(client);

        Client result = clientService.withdrawById(id, request);

        assertNotNull(result);

        verify(clientGateway).getClientById(id);
        verify(validationService).validationPositiveBalance(request.getAmount());
        verify(validationService).validationBalanceTransaction(client.getBalance(), request.getAmount());
        verify(clientGateway).withdrawById(client, request);
    }

    @Test
    @DisplayName("Should throw exception when amount is invalid")
    void ExceptionWhenAmountIsInvalid() {

        Long id = 1L;

        Client client = new Client();
        client.setClientId(id);
        client.setBalance(new BigDecimal("100.00"));

        WithdrawRequest request = new WithdrawRequest();
        request.setAmount(new BigDecimal("-10.00"));

        when(clientGateway.getClientById(id)).thenReturn(client);

        doThrow(new InvalidBalanceException("Invalid", ErrorType.INVALID_VALUE))
                .when(validationService)
                .validationPositiveBalance(request.getAmount());

        assertThrows(InvalidBalanceException.class, () ->
                clientService.withdrawById(id, request)
        );

        verify(validationService).validationPositiveBalance(request.getAmount());
        verify(clientGateway, never()).withdrawById(any(), any());
    }

    @Test
    @DisplayName("Should throw exception when balance is insufficient")
    void ExceptionWhenBalanceIsInsufficient() {

        Long id = 1L;

        Client client = new Client();
        client.setClientId(id);
        client.setBalance(new BigDecimal("50.00"));

        WithdrawRequest request = new WithdrawRequest();
        request.setAmount(new BigDecimal("100.00"));

        when(clientGateway.getClientById(id)).thenReturn(client);

        doThrow(new InvalidBalanceException("Insufficient funds", ErrorType.INVALID_VALUE))
                .when(validationService)
                .validationBalanceTransaction(client.getBalance(), request.getAmount());

        assertThrows(InvalidBalanceException.class, () ->
                clientService.withdrawById(id, request)
        );

        verify(validationService).validationBalanceTransaction(client.getBalance(), request.getAmount());
        verify(clientGateway, never()).withdrawById(any(), any());
    }

    @Test
    @DisplayName("Should transfer successfully")
    void TransferSuccessfully(){

    }
}