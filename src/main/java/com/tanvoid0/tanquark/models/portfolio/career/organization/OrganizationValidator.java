package com.tanvoid0.tanquark.models.portfolio.career.organization;

import com.tanvoid0.tanquark.common.exception.InvalidRequestException;
import com.tanvoid0.tanquark.common.exception.ValidationProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class OrganizationValidator {
    private final Validator validator;

    public void validate(final NewOrganizationVO request) {
        final List<ValidationProperty> validations = new ArrayList<>();

        InvalidRequestException.validate(validations, validator, request);
    }
}
