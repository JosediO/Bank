package com.example.demo.web.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequest {

    private Long id;
    private Long receiverId;
    private Integer amount;
}
