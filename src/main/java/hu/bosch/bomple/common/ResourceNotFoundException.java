package hu.bosch.bomple.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private final Class<?> clazz;
    private final String id;

    public ResourceNotFoundException(Class<?> clazz, String id) {
        super();
        this.clazz = clazz;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format("No %s found with ID: %s", clazz.getSimpleName(), id);
    }

}
