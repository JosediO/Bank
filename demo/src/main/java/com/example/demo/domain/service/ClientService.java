package com.example.demo.domain.service;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.enums.ClientStatus;
import com.example.demo.domain.enums.ErrorType;
import com.example.demo.domain.exceptions.DomainException;

import com.example.demo.domain.exceptions.NotFoundException;
import com.example.demo.domain.gateway.ClientGateway;
import com.example.demo.web.dto.request.DepositRequest;
import com.example.demo.web.dto.request.UpdateRequest;
import com.example.demo.web.dto.request.WithdrawRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class ClientService {

    @Autowired
    private ClientGateway clientGateway;

    @Autowired
    private ValidationService validationService;

    public Client getClientById(Long id) throws DomainException {
        Client client = clientGateway.getClientById(id);
        log.info("Start Search Client By Id: " + id);
        if (client == null){
            log.info("Search result id: " + id + "Not Found or Not Exist");
            throw new NotFoundException("Search result id: " + id + " dNot Found or Not Exist", ErrorType.NULL);

        }
        log.info("Search result: Client from id: "+id+" founded.");
        return client;
    }

    public Client createClient(Client client) throws DomainException{
        validationService.validationCreateClient(client);
        log.info("Starting create new client...");
        return clientGateway.createClient(client);
    }

    public Client updateClient(Long id, UpdateRequest updateRequest) {
        Client client = getClientById(id);
        validationService.validationClientName(updateRequest.getName());
        validationService.validationCpf(updateRequest.getCpf());
        validationService.validationStatus((ClientStatus) updateRequest.getStatus());
        log.info("Starting update client with id: " + id);
        return clientGateway.updateClient(client, updateRequest);
    }
    public Client deletClient(Long id) throws DomainException{
        Client client = getClientById(id);
        return clientGateway.deletClient(client);
    }

    public Client depositById(Long id, DepositRequest depositRequest){
        Client client = getClientById(id);
        validationService.validationPositiveBalance(depositRequest.getAmount());
        log.info("Starting deposit to client with id: " + id);
        return clientGateway.depositById(client,depositRequest);
    }

    public Client withdrawById(Long id, WithdrawRequest withdrawRequest){
        Client client = getClientById(id);
        validationService.validationPositiveBalance(withdrawRequest.getAmount());
        validationService.validationBalanceTransaction(client.getBalance(),withdrawRequest.getAmount());
        log.info("Starting a withdraw on client with id: " + id);
        return clientGateway.withdrawById(client,withdrawRequest);
    }

}
