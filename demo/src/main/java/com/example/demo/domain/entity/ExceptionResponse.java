package com.example.demo.domain.entity;

import com.example.demo.domain.enums.ErrorType;

public class ExceptionResponse {

    private String errorMessage;
    private ErrorType type;

    public ExceptionResponse(String errorMessage, ErrorType type) {
        this.errorMessage = errorMessage;
        this.type = type;
    }
}
