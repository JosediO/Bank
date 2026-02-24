package com.example.demo.domain.exceptions;

import com.example.demo.domain.enums.ErrorType;

public class InvalidAccountException extends DomainException {

    public InvalidAccountException() {
        super("The Account is invalid. Try Again",
                ErrorType.INVALID_FORMAT);
    }

}