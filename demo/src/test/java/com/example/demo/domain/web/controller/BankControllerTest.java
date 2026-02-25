package com.example.demo.domain.web.controller;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.service.ClientService;
import com.example.demo.web.controller.BankController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
    void shouldReturnClientWhenIdExists() throws Exception {

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
    void shouldCreateClient() throws Exception {

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
}
