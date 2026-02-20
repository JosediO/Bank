package com.example.demo.domain.exceptions;

import com.example.demo.domain.enums.ErrorType;

public class NotActiveException extends DomainException {

    public NotActiveException() {
        super("Not Active. Please try Again", ErrorType.INACTIVE);
    }

}