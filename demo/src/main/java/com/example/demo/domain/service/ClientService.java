package com.example.demo.domain.service;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.exceptions.DomainException;

import com.example.demo.domain.exceptions.NotFoundException;
import com.example.demo.domain.gateway.ClientGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientService {

    @Autowired
    private ClientGateway clientGateway;

    public Client getClientById(Long id) throws DomainException {
        Client client = clientGateway.getClientById(id);
        log.info("Start Search Client By Id: " + id);
        if (client == null){
            log.info("Search result: " + id + "Not Found or Not Exist");
            throw new NotFoundException();

        }
        log.info("Search result: Client from "+id+" found.");
        return client;
    }


}
