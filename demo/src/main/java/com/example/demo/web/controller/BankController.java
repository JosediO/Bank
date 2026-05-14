package com.example.demo.web.controller;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.service.ClientService;
import com.example.demo.web.dto.request.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/clients")
@Tag(name = "Clients", description = "Endpoints to manage clients")
public class BankController {

    private final ClientService clientService;

    public BankController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Search client for ID.")
    public ResponseEntity<Client> getClientById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new client.")
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto){
        Client client = toEntity(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(clientService.createClient(client)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a client.")
    public ResponseEntity<Client> updateUser(@PathVariable Long id, @RequestBody UpdateRequest updateRequest) {
        return ResponseEntity.ok(clientService.updateClient(id, updateRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Logic or soft delet a client.")
    public ResponseEntity<Client> delentClient(@PathVariable Long id){
        return ResponseEntity.ok(clientService.deletClient(id));
    }

    @PutMapping("/{id}/deposit")
    @Operation(summary = "Deposit operation with client id.")
    public ResponseEntity<Client> depositById(@PathVariable Long id, @RequestBody DepositRequest depositRequest){
        return ResponseEntity.ok(clientService.depositById(id,depositRequest));
    }

    @PutMapping("/{id}/withdraw")
    @Operation(summary = "Withdraw operation with client id.")
    public ResponseEntity<Client> withdrawById(@PathVariable Long id, @RequestBody WithdrawRequest withdrawRequest){
        return ResponseEntity.ok(clientService.withdrawById(id,withdrawRequest));
    }

    @PutMapping("/{id}/transfer")
    @Operation(summary = "Transfer operation with client id.")
    public ResponseEntity<Client> transferById(@PathVariable Long id, @RequestBody TransferRequest transferRequest){
        return ResponseEntity.ok(clientService.transferById(id,transferRequest));
    }

    private ClientDto toDto(Client client) {
        return new ClientDto(
                client.getAccount(),
                client.getName(),
                client.getCpf(),
                client.getBalance(),
                client.getStatus()
        );
    }

    private Client toEntity(ClientDto dto) {
        Client client = new Client();
        client.setAccount(dto.getAccount());
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setBalance(dto.getBalance());
        client.setStatus(dto.getStatus());
        return client;
    }
}
