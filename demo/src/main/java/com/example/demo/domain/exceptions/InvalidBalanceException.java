package com.example.demo.domain.exceptions;

import com.example.demo.domain.enums.ErrorType;

public class InvalidBalanceException extends DomainException {

    public InvalidBalanceException(String msg, ErrorType errorType) {
        super(msg,errorType);
    }

}