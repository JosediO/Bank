package domain.exceptions;

import domain.enums.ErrorType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends Exception {

    private String errorMessage;
    private ErrorType type;

    public NotFoundException(String errorMessage, ErrorType type) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.type = type;
    }

}
