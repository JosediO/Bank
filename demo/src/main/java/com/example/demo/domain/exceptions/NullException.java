package com.example.demo.domain.exceptions;

import com.example.demo.domain.enums.ErrorType;

public class NullException extends DomainException {

    public NullException() {
        super("Null, Please try again",ErrorType.NULL);
    }

}