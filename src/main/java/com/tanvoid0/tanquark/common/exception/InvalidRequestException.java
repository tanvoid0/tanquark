package com.tanvoid0.tanquark.common.exception;

import jakarta.validation.ConstraintViolation;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class InvalidRequestException extends RuntimeException implements Serializable {
    private final List<ValidationProperty> validations;

    public InvalidRequestException(final List<ValidationProperty> constraints) {
        super("Validation failed");
        this.validations = constraints;
    }

    private List<ValidationProperty> fromConstraint(final Set<ConstraintViolation<?>> constraints) {
        return constraints.stream().map(item ->
                new ValidationProperty(item.getPropertyPath().toString(), item.getMessage())
        ).collect(Collectors.toList());
    }

    public void add(final ValidationProperty property) {
        validations.add(property);
    }

    public void add(final Set<ConstraintViolation<?>> constraints) {
        validations.addAll(fromConstraint(constraints));
    }
}
