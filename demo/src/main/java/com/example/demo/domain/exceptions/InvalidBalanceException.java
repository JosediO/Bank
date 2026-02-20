package com.example.demo.domain.exceptions;

import com.example.demo.domain.enums.ErrorType;

public class InvalidBalanceException extends DomainException {

    public InvalidBalanceException() {
        super("Saldo insuficiente para realizar a operação",
                ErrorType.INVALID_VALUE);
    }

}