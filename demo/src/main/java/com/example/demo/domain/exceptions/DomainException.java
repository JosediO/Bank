package com.example.demo.domain.exceptions;

import com.example.demo.domain.enums.ErrorType;
import lombok.Getter;

@Getter
public abstract class DomainException extends RuntimeException {

    private final ErrorType type;

    protected DomainException(String message, ErrorType type) {
        super(message);
        this.type = type;
    }
    
}
