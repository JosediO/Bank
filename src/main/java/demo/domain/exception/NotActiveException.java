package demo.domain.exception;

import demo.domain.enums.ErrorType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotActiveException extends Exception {

    private String errorMessage;
    private ErrorType type;

    public NotActiveException(String errorMessage, ErrorType type) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.type = type;
    }

}
