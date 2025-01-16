package demo.domain.exception;

import demo.domain.enums.ErrorType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidNameException extends Exception {

    private String errorMessage;
    private ErrorType type;

    public InvalidNameException(String errorMessage, ErrorType type) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.type = type;
    }

}
