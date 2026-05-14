package com.example.demo.domain.exceptions;

import com.example.demo.domain.enums.ErrorType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends DomainException {

    public NotFoundException(String msg, ErrorType errorType) {
        super(msg,errorType);
    }

}
