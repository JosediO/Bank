package com.example.demo.resources.dao;

import com.example.demo.domain.entity.Client;
import com.example.demo.domain.enums.ClientStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class ClientDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;
    private String account;
    private String name;
    private String cpf;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ClientStatus status;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Client daoToEntity(){
        Client client = new Client();
        client.setClientId(clientId);
        client.setAccount(account);
        client.setName(name);
        client.setCpf(cpf);
        client.setBalance(balance);
        client.setStatus(status);
        client.setCreatedAt(createdAt);
        client.setUpdatedAt(updatedAt);
        return client;
    }
}
