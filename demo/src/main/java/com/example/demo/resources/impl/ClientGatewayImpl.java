package com.example.demo.resources.impl;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.enums.ClientStatus;
import com.example.demo.domain.exceptions.*;
import com.example.demo.domain.gateway.ClientGateway;
import com.example.demo.resources.dao.ClientDao;
import com.example.demo.resources.database.ClientRepository;
import com.example.demo.web.dto.request.DepositRequest;
import com.example.demo.web.dto.request.TransferRequest;
import com.example.demo.web.dto.request.UpdateRequest;
import com.example.demo.web.dto.request.WithdrawRequest;
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

    @Override
    public Client updateClient(Client client, UpdateRequest updateRequest) {
        if (updateRequest.getName() != null) {
            client.setName(updateRequest.getName());
        }
        if (updateRequest.getCpf() != null) {
            client.setCpf(updateRequest.getCpf());
        }
        if (updateRequest.getStatus() != null) {
            client.setStatus((ClientStatus) updateRequest.getStatus());
        }
        client.setUpdatedAt(LocalDateTime.now());
        clientRepository.save(toDao(client));
        log.info("Client successfully updated.");
        return client;
    }

    public Client deletClient(Client client){
        client.setStatus(ClientStatus.DESACTIVED);
        ClientDao clientDao = toDao(client);
        clientRepository.save(clientDao);
        log.info("Client successfully deleted.");
        return client;
    }

    public Client depositById(Client client, DepositRequest depositRequest){
        client.setBalance(client.getBalance().add(depositRequest.getAmount()));
        clientRepository.save(toDao(client));
        log.info("Deposit successfully.");
        return client;
    }

    public Client withdrawById(Client client, WithdrawRequest withdrawRequest){
        client.setBalance(client.getBalance().subtract(withdrawRequest.getAmount()));
        clientRepository.save(toDao(client));
        log.info("Withdraw successfully.");
        return client;
    }

    public Client transferById(Client client, Client receiver, TransferRequest transferRequest){
        client.setBalance(client.getBalance().subtract(transferRequest.getAmount()));
        receiver.setBalance(receiver.getBalance().add(transferRequest.getAmount()));
        clientRepository.save(toDao(client));
        clientRepository.save(toDao(receiver));
        log.info("Transfer Request as successfully.");
        return client;
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
