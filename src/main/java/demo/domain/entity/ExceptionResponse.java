package demo.domain.entity;

import demo.domain.enums.ErrorType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
    private String errorMessage;
    private ErrorType type;

    public ExceptionResponse(String errorMessage, ErrorType type) {
        this.errorMessage = errorMessage;
        this.type = type;
    }

}