package domain.exceptions;

import domain.enums.ErrorType;

public class NotActiveException extends  Exception {

    private String errorMessage;
    private ErrorType type;

    public NotActiveException(String errorMessage, ErrorType type) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.type = type;
    }

}