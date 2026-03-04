package com.example.demo.domain.gateway;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.exceptions.*;
import com.example.demo.web.dto.request.ClientDto;

public interface ClientGateway {

    public Client getClientById(Long Id) throws DomainException;
    public Client createClient (Client client) throws DomainException;

}
