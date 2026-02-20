package com.example.demo.web.controller;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class BankController {

    private final ClientService clientService;

    public BankController(ClientService clientService) {
        this.clientService = clientService;
    }



    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id){
        return clientService.getClientById(id);
    }

}
