package com.example.demo.web.dto.request;


import com.example.demo.domain.enums.ClientStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private String account;
    private String name;
    private String cpf;
    private BigDecimal balance;
    private ClientStatus status;
}
