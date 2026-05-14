package com.example.demo.web.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferRequest {

    private Long receiverId;
    private BigDecimal amount;
}
