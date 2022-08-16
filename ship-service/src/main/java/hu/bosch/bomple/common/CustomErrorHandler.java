package hu.bosch.bomple.common;

import hu.bosch.bomple.api.model.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AbstractBompleException.class})
    public ResponseEntity<ErrorMessage> handleAbstractErrorCodeException(AbstractBompleException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getErrorMessage());
    }

}
