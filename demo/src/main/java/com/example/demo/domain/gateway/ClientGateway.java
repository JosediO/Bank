package com.example.demo.domain.gateway;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.exceptions.*;
import com.example.demo.web.dto.UpdateRequest;

public interface ClientGateway {

    public Client createClient (Client client)  throws InvalidNameException, InvalidCpfException, InvalidBalanceException,NotActiveException;
    public Client updateClient (Client client, UpdateRequest updateRequest) throws NullException, NotFoundException,InvalidNameException, InvalidCpfException,NotActiveException;
    public Client deletClient (Client client);
    public Client getClientById(Long Id) throws DomainException;
    public Client changeStatus(Client client) throws NotFoundException, NullException;
    public Client transferTo(Long id, Long receiverId, Integer amount) throws NotFoundException, NullException;
    public Client withdrawById(Client client, Integer amount) throws NullException, NotFoundException, InvalidBalanceException;
    public Client depositById(Client client, Integer amount) throws NullException, NotFoundException;
    public Client checkAccount(Client client) throws NullException,NotFoundException;
}
