package com.tanvoid0.tanquark.models.portfolio;

import com.tanvoid0.tanquark.common.exception.InvalidRequestException;
import com.tanvoid0.tanquark.common.exception.ValidationProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Validator;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PortfolioUserValidator {

    @Inject
    Validator validator;

    @Inject
    PortfolioUserRepository portfolioUserRepository;

    public void validate(final NewPortfolioUserVO request, final String username) {
        final List<ValidationProperty> violations = new ArrayList<>();
        if (portfolioUserRepository.findByUserUsername(username).isPresent()) {
            violations.add(ValidationProperty.of("username", "Portfolio User already exists with username " + username));
        }
        InvalidRequestException.validate(violations, validator, request);
    }

}
