package com.example.demo.domain.exceptions;

import com.example.demo.domain.enums.ErrorType;

public class InvalidAccountException extends DomainException {

    public InvalidAccountException(String msg, ErrorType errorType) {
        super(msg,errorType);
    }

}