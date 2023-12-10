package com.tanvoid0.tanquark.common.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 607828645187469385L;
    
    private final String resourceName;
    private final String fieldName;
    private final String fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s with %s='%s' not found.", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        this(resourceName, fieldName, String.valueOf(fieldValue));
    }
}