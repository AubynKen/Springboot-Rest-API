package net.springrestapi.restapidemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName, fieldName;
    private Long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("%s with %s: %d is not found.", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
        this.fieldName = fieldName;
    }
}
