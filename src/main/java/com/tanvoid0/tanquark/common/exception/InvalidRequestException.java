package com.tanvoid0.tanquark.common.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class InvalidRequestException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = 914119762951859778L;

    private final List<ValidationProperty> validations;

    public InvalidRequestException(final List<ValidationProperty> constraints) {
        super("Validation failed");
        this.validations = constraints;
    }

    private static List<ValidationProperty> fromConstraint(final Set<ConstraintViolation<?>> constraints) {
        return constraints.stream().map(item ->
                new ValidationProperty(item.getPropertyPath().toString(), item.getMessage())
        ).collect(Collectors.toList());
    }

    public static <T> void validate(final List<ValidationProperty> violations, final Validator validator, final T request) {
        final Set<ConstraintViolation<?>> constraintViolations = new HashSet<>(validator.validate(request));

        if (!constraintViolations.isEmpty()) {
            violations.addAll(InvalidRequestException.fromConstraint(constraintViolations));
        }
        if (!violations.isEmpty()) {
            throw new InvalidRequestException(violations);
        }

    }

    public void add(final ValidationProperty property) {
        validations.add(property);
    }

    public void add(final Set<ConstraintViolation<?>> constraints) {
        validations.addAll(fromConstraint(constraints));
    }
}
