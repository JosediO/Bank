package com.example.demo.web.dto.request;

import com.example.demo.domain.enums.ClientStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRequest {

    private String name;
    private String cpf;
    private ClientStatus status;
}
