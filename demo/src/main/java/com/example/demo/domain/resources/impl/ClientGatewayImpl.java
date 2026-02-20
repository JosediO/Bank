package com.example.demo.domain.resources.impl;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.exceptions.*;
import com.example.demo.domain.gateway.ClientGateway;
import com.example.demo.domain.resources.dao.ClientDao;
import com.example.demo.domain.resources.database.ClientRepository;
import com.example.demo.web.dto.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ClientGatewayImpl implements ClientGateway {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) throws InvalidNameException, InvalidCpfException, InvalidBalanceException, NotActiveException {
        return null;
    }

    @Override
    public Client updateClient(Client client, UpdateRequest updateRequest) throws NullException, NotFoundException, InvalidNameException, InvalidCpfException, NotActiveException {
        return null;
    }

    @Override
    public Client deletClient(Client client) {
        return null;
    }

    @Override
    public Client getClientById(Long id) throws DomainException {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return toEntity(optionalClient.get());
    }

    @Override
    public Client changeStatus(Client client) throws NotFoundException, NullException {
        return null;
    }

    @Override
    public Client transferTo(Long id, Long receiverId, Integer amount) throws NotFoundException, NullException {
        return null;
    }

    @Override
    public Client withdrawById(Client client, Integer amount) throws NullException, NotFoundException, InvalidBalanceException {
        return null;
    }

    @Override
    public Client depositById(Client client, Integer amount) throws NullException, NotFoundException {
        return null;
    }

    @Override
    public Client checkAccount(Client client) throws NullException, NotFoundException {
        return null;
    }

    private Client toEntity(Client clientDao){
        if(clientDao == null){
            throw new NullException();
        }
        Client client = new Client();
        client.setClientId(clientDao.getClientId());
        client.setAccount(clientDao.getAccount());
        client.setName(clientDao.getName());
        client.setCpf(clientDao.getCpf());
        client.setBalance(clientDao.getBalance());
        client.setStatus(clientDao.getStatus());
        client.setCreatedAt(clientDao.getCreatedAt());
        client.setUpdatedAt(LocalDateTime.now());
        return client;
    }

    private ClientDao toDao(Client client){
        ClientDao clientDao = new ClientDao();
        clientDao.setClientId(client.getClientId());
        clientDao.setAccount(client.getAccount());
        clientDao.setName(client.getName());
        clientDao.setCpf(client.getCpf());
        clientDao.setBalance(client.getBalance());
        clientDao.setStatus(client.getStatus());
        clientDao.setCreatedAt(LocalDateTime.now());
        clientDao.setUpdatedAt(LocalDateTime.now());
        return clientDao;
    }

    private ClientDao entityToDao (Client client){
        if(client == null){
            throw new NullException();
        }
        ClientDao clientDao = new ClientDao();
        clientDao.setClientId(client.getClientId());
        clientDao.setAccount(client.getAccount());
        clientDao.setName(client.getName());
        clientDao.setCpf(client.getCpf());
        clientDao.setBalance(client.getBalance());
        clientDao.setStatus(client.getStatus());
        clientDao.setCreatedAt(client.getCreatedAt());
        clientDao.setUpdatedAt(LocalDateTime.now());
        return clientDao;
    }
}
