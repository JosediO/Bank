package com.example.demo.domain.exceptions;

import com.example.demo.domain.enums.ErrorType;

public class InvalidCpfException extends DomainException {

    public InvalidCpfException() {
        super("O CPF é invalido. Tente novamente",
                ErrorType.INVALID_FORMAT);
    }

}