package com.example.demo.domain.gateway;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.exceptions.*;
import com.example.demo.web.dto.request.ClientDto;
import com.example.demo.web.dto.request.DepositRequest;
import com.example.demo.web.dto.request.UpdateRequest;

import java.math.BigDecimal;

public interface ClientGateway {

    public Client getClientById (Long Id) throws DomainException;
    public Client createClient (Client client) throws DomainException;
    public Client updateClient (Client client, UpdateRequest updateRequest) throws DomainException;
    public Client deletClient (Client client) throws DomainException;
    public Client depositById (Client client, DepositRequest depositRequest) throws DomainException;
}
