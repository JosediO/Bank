package com.example.demo.domain.entity;

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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;
    private String account;
    private String name;
    private String cpf;
    private BigDecimal balance;
    private ClientStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
