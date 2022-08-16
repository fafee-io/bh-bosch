package hu.bosch.bomple.common;

import hu.bosch.bomple.api.model.ErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class AbstractBompleException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final ErrorCode errorCode;
    private final String additionalMessage;

    @Override
    public String getMessage() {
        return errorCode.toString().concat(additionalMessage);
    }

    public ErrorMessage getErrorMessage() {
        ErrorMessage msg = this.errorCode.toErrorMessage();
        msg.setAdditionalMessage(this.additionalMessage);

        return msg;
    }
}
