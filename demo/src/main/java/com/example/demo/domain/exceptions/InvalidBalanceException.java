package com.example.demo.domain.exceptions;

import com.example.demo.domain.enums.ErrorType;

public class InvalidBalanceException extends DomainException {

    public InvalidBalanceException() {
        super("Insufficient funds to complete the transaction.",
                ErrorType.INVALID_VALUE);
    }

}