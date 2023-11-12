package com.tanvoid0.tanquark.models.user;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public Optional<User> findByEmail(final String email) {
        return find("email", email).firstResultOptional();
    }

    public Optional<User> findByUsername(final String username) {
        return find("username", username).firstResultOptional();
    }
}
