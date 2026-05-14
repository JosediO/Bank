package com.example.demo.domain.exceptions;

import com.example.demo.domain.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.domain.entity.ExceptionResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ExceptionResponse> handleDomainException(DomainException ex){

        HttpStatus status = mapStatus(ex.getType());

        ExceptionResponse response =
                new ExceptionResponse(ex.getMessage(), ex.getType());

        return ResponseEntity.status(status).body(response);
    }

    private HttpStatus mapStatus(ErrorType type) {
        return switch (type) {
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case INVALID_VALUE, INVALID_FORMAT, NULL -> HttpStatus.BAD_REQUEST;
            case NOT_DELETED, NOT_UPDATED -> HttpStatus.CONFLICT;
            case INACTIVE -> HttpStatus.FORBIDDEN;
        };
    }
}
