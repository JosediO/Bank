package domain.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import domain.entity.ExceptionResponse;

@ControllerAdvice
public class ExceptionHandler  {

    public ResponseEntity<ExceptionResponse> handleNotFound(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(ex.getErrorMessage(), ex.getType())
        );
    }
}
