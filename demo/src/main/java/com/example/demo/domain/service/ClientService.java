package com.example.demo.domain.service;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.exceptions.DomainException;

import com.example.demo.domain.exceptions.NotFoundException;
import com.example.demo.domain.gateway.ClientGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientGateway clientGateway;

    public Client getClientById(Long id) throws DomainException {
        Client client = clientGateway.getClientById(id);
        if (client == null){
            throw new NotFoundException();
        }
        return client;
    }



}
