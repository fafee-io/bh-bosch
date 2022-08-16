package hu.bosch.bomple.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotYetImplementedException extends AbstractBompleException {

    private final String operation;

    public NotYetImplementedException(String operation) {
        super(HttpStatus.NOT_IMPLEMENTED, ErrorCode.NOT_IMPLEMENTED, operation);
        this.operation = operation;
    }
}
