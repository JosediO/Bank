package domain.exceptions;

import domain.enums.ErrorType;

public class InvalidCpfException extends  Exception {

    private String errorMessage;
    private ErrorType type;

    public InvalidCpfException(String errorMessage, ErrorType type) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.type = type;
    }

}