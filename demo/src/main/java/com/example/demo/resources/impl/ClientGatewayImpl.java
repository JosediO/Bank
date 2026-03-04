package com.example.demo.resources.impl;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.exceptions.*;
import com.example.demo.domain.gateway.ClientGateway;
import com.example.demo.resources.dao.ClientDao;
import com.example.demo.resources.database.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;


@Slf4j
@Component
public class ClientGatewayImpl implements ClientGateway {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client getClientById(Long id) throws DomainException {
        Optional<ClientDao> optionalClientDao = clientRepository.findById(id);
        ClientDao clientDao = optionalClientDao.get();
        return toEntity(clientDao);
    }

    @Override
    public Client createClient(Client client){
        ClientDao clientDao = toDao(client);
        ClientDao saveClient = clientRepository.save(clientDao);
        log.info("Client successfully created.");
        return saveClient.daoToEntity();
    }


    private Client toEntity(ClientDao clientDao){
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

}
