package com.example.demo.domain.gateway;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.exceptions.*;
import com.example.demo.web.dto.request.*;

public interface ClientGateway {

    public Client getClientById (Long Id) throws DomainException;
    public Client createClient (Client client) throws DomainException;
    public Client updateClient (Client client, UpdateRequest updateRequest) throws DomainException;
    public Client deletClient (Client client) throws DomainException;
    public Client depositById (Client client, DepositRequest depositRequest) throws DomainException;
    public Client withdrawById (Client client, WithdrawRequest withdrawRequest) throws DomainException;
    public Client transferById(Client client, Client receiver, TransferRequest transferRequest);
}
