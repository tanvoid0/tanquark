package com.tanvoid0.tanquark.config.auth.validator;

import com.tanvoid0.tanquark.common.exception.InvalidRequestException;
import com.tanvoid0.tanquark.common.exception.ValidationProperty;
import com.tanvoid0.tanquark.config.auth.vo.LoginRequestVO;
import com.tanvoid0.tanquark.config.auth.vo.RegisterRequestVO;
import com.tanvoid0.tanquark.models.user.User;
import com.tanvoid0.tanquark.models.user.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class AuthValidator {

    @Inject
    UserRepository userRepository;

    @Inject
    Validator validator;

    public void validate(final RegisterRequestVO request) {

        Set<ConstraintViolation<?>> constraintViolations = new HashSet<>(validator.validate(request));
        List<ValidationProperty> violations = new ArrayList<>();

        final Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()) {
            violations.add(new ValidationProperty("email", String.format("User with email %s already exists", request.getEmail())));
        }
        if (!violations.isEmpty() || !constraintViolations.isEmpty()) {
            final InvalidRequestException ex = new InvalidRequestException(violations);
            if (!constraintViolations.isEmpty()) {
                ex.add(constraintViolations);
            }
            throw ex;
        }
    }

    public User validate(final LoginRequestVO request) {
        final Set<ConstraintViolation<?>> constraintViolations = new HashSet<>(validator.validate(request));

        List<ValidationProperty> violations = new ArrayList<>();

        final Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (user.isEmpty()) {
            violations.add(new ValidationProperty("email", String.format("No user with email %s found", request.getEmail())));
        } else {
            if (!user.get().getPassword().equals(request.getPassword())) {
                violations.add(ValidationProperty.builder().property("password").value("Invalid Password").build());
            }
        }
        if (!violations.isEmpty() || !constraintViolations.isEmpty()) {
            final InvalidRequestException ex = new InvalidRequestException(violations);
            if (!constraintViolations.isEmpty()) {
                ex.add(constraintViolations);
            }
            throw ex;
        }
        return user.get();
    }

    public final User validate(final long id) {
        return userRepository.findById(id);
    }
}
