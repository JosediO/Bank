package com.example.demo.domain.exceptions;

import com.example.demo.domain.enums.ErrorType;

public class InvalidCpfException extends DomainException {

    public InvalidCpfException(String msg, ErrorType errorType) {
        super(msg,errorType);
    }

}