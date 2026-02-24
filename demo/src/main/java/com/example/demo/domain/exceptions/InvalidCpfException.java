package com.example.demo.domain.exceptions;

import com.example.demo.domain.enums.ErrorType;

public class InvalidCpfException extends DomainException {

    public InvalidCpfException() {
        super("The CPF is invalid. Try Again",
                ErrorType.INVALID_FORMAT);
    }

}