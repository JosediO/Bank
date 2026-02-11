package domain.exceptions;

import domain.enums.ErrorType;

public class NullException extends  Exception {

    private String errorMessage;
    private ErrorType type;

    public NullException(String errorMessage, ErrorType type) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.type = type;
    }

}