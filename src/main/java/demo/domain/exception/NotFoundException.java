package demo.domain.exception;

import demo.domain.enums.ErrorType;

public class NotFoundException extends Exception {

    private String errorMessage;
    private ErrorType type;

    public NotFoundException(String errorMessage, ErrorType type) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.type = type;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorType getType() {
        return type;
    }

    public void setType(ErrorType type) {
        this.type = type;
    }
}
