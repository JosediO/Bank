package com.example.demo.domain.web.controller;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.enums.ClientStatus;
import com.example.demo.domain.enums.ErrorType;
import com.example.demo.domain.exceptions.GlobalExceptionHandler;
import com.example.demo.domain.exceptions.InvalidBalanceException;
import com.example.demo.domain.exceptions.NotFoundException;
import com.example.demo.domain.service.ClientService;
import com.example.demo.web.controller.BankController;
import com.example.demo.web.dto.request.DepositRequest;
import com.example.demo.web.dto.request.TransferRequest;
import com.example.demo.web.dto.request.UpdateRequest;
import com.example.demo.web.dto.request.WithdrawRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;



import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BankController.class)
@Import(GlobalExceptionHandler.class)
public class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should return client when id exists")
    void returnClientExistsId() throws Exception {

        Client client = new Client();
        client.setClientId(1L);
        client.setName("John Doe");

        when(clientService.getClientById(1L)).thenReturn(client);

        mockMvc.perform(get("/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(clientService).getClientById(1L);
    }

    @Test
    @DisplayName("Should create client successfully")
    void createClientSuccess() throws Exception {

        Client input = new Client();
        input.setName("John Doe");

        Client saved = new Client();
        saved.setClientId(1L);
        saved.setName("John Doe");

        when(clientService.createClient(any(Client.class)))
                .thenReturn(saved);

        mockMvc.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                        .post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input))
        );

        verify(clientService).createClient(any(Client.class));
    }

    @Test
    @DisplayName("Should update client successfully")
    void shouldUpdateClient() throws Exception {

        Long id = 1L;

        UpdateRequest request = new UpdateRequest();
        request.setName("João");
        request.setCpf("12345678900");
        request.setStatus(ClientStatus.ACTIVE);

        Client client = new Client();
        client.setClientId(id);
        client.setAccount("A1234");
        client.setName("João");
        client.setCpf("12345678900");
        client.setBalance(BigDecimal.valueOf(1000.0));
        client.setStatus(ClientStatus.ACTIVE);

        when(clientService.updateClient(eq(id), any(UpdateRequest.class)))
                .thenReturn(client);

        mockMvc.perform(put("/clients/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "João",
                                  "cpf": "12345678900",
                                  "status": "ACTIVE"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("João"))
                .andExpect(jsonPath("$.cpf").value("12345678900"))
                .andExpect(jsonPath("$.status").value("ACTIVE"));

        verify(clientService).updateClient(eq(id), any(UpdateRequest.class));
    }

    @Test
    @DisplayName("Should logic delet client success.")
    void deletClientSuccess() throws Exception {

        mockMvc.perform(delete("/clients/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should deposit successfully")
    void DepositSuccessfully() throws Exception {

        Long id = 1L;

        DepositRequest request = new DepositRequest();
        request.setAmount(new BigDecimal("100.00"));

        Client client = new Client();
        client.setClientId(id);
        client.setAccount("A1234");
        client.setName("João");
        client.setCpf("12345678900");
        client.setBalance(new BigDecimal("1100.00"));
        client.setStatus(ClientStatus.ACTIVE);

        when(clientService.depositById(eq(id), any(DepositRequest.class)))
                .thenReturn(client);

        mockMvc.perform(put("/clients/{id}/deposit", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "amount": 100.00
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId").value(id))
                .andExpect(jsonPath("$.balance").value(1100.00))
                .andExpect(jsonPath("$.status").value("ACTIVE"));

        verify(clientService).depositById(eq(id), any(DepositRequest.class));
    }

    @Test
    @DisplayName("Should withdraw successfully")
    void WithdrawSuccessfully() throws Exception {

        Long id = 1L;

        WithdrawRequest request = new WithdrawRequest();
        request.setAmount(new BigDecimal("50.00"));

        Client client = new Client();
        client.setClientId(id);
        client.setAccount("A1234");
        client.setName("João");
        client.setCpf("12345678900");
        client.setBalance(new BigDecimal("50.00"));
        client.setStatus(ClientStatus.ACTIVE);

        when(clientService.withdrawById(eq(id), any(WithdrawRequest.class)))
                .thenReturn(client);

        mockMvc.perform(put("/clients/{id}/withdraw", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "amount": 50.00
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId").value(id))
                .andExpect(jsonPath("$.balance").value(50.00))
                .andExpect(jsonPath("$.status").value("ACTIVE"));

        verify(clientService).withdrawById(eq(id), any(WithdrawRequest.class));
    }

    @Test
    @DisplayName("Should transfer successfully")
    void TransferSuccessfully() throws Exception {

        Long id = 1L;

        Client client = new Client();
        client.setClientId(id);
        client.setBalance(BigDecimal.valueOf(1000));

        String requestJson = """
                {
                "amount": 100
                 }
                """;

        when(clientService.transferById(eq(id), any()))
                .thenReturn(client);

        mockMvc.perform(put("/clients/{id}/transfer", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());

        verify(clientService).transferById(eq(id), any());

    }

    @Test
    @DisplayName("Should return 400 when transfer amount is invalid")
    void ReturnBadRequestWhenTransferInvalid() throws Exception {

        Long id = 1L;

        String requestJson = """
                {
                "amount": -50
                }
                """;

        when(clientService.transferById(eq(id), any()))
                .thenThrow(new InvalidBalanceException(
                        "Invalid amount",
                        ErrorType.INVALID_VALUE
                ));

        mockMvc.perform(put("/clients/{id}/transfer", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());

        verify(clientService).transferById(eq(id), any());

    }

    @Test
    void shouldReturnNotFoundWhenClientDoesNotExist() throws Exception {

        Long id = 1L;

            String requestJson = """
            {
                "amount": 100
            }
            """;

        when(clientService.transferById(eq(id), any()))
                .thenThrow(new NotFoundException(
                        "Client not found",
                        ErrorType.NULL
                ));

        mockMvc.perform(put("/clients/{id}/transfer", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isNotFound());

        verify(clientService).transferById(eq(id), any());
    }
}
