package domain.exceptions;

import domain.enums.ErrorType;

public class InvalidBalanceException extends  Exception {

    private String errorMessage;
    private ErrorType type;

    public InvalidBalanceException(String errorMessage, ErrorType type) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.type = type;
    }

}