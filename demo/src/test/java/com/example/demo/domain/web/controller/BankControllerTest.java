package com.example.demo.domain.web.controller;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.enums.ClientStatus;
import com.example.demo.domain.service.ClientService;
import com.example.demo.web.controller.BankController;
import com.example.demo.web.dto.request.UpdateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

        mockMvc.perform(put("/clients/{id}/update", id)
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

    @DisplayName("Should logic delet client success.")
    void deletClientSuccess() throws Exception {

        mockMvc.perform(delete("/clients/1"))
                .andExpect(status().isOk());
    }
}
