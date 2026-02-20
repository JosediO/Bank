package com.example.demo.domain.exceptions;

import com.example.demo.domain.enums.ErrorType;

public class InvalidNameException extends DomainException {

    public InvalidNameException() {
        super("The name is invalid, please try again.", ErrorType.INVALID_FORMAT);
    }

}