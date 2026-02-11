package domain.exceptions;

import domain.enums.ErrorType;

public class InvalidNameException extends  Exception {

    private String errorMessage;
    private ErrorType type;

    public InvalidNameException(String errorMessage, ErrorType type) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.type = type;
    }

}