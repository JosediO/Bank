package com.example.demo.domain.exceptions;

import com.example.demo.domain.enums.ErrorType;

public class InvalidNameException extends DomainException {

    public InvalidNameException(String msg, ErrorType errorType) {
        super(msg,errorType);
    }

}