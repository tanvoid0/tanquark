package com.tanvoid0.tanquark.models.portfolio;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class PortfolioUserRepository implements PanacheRepository<PortfolioUser> {

    public Optional<PortfolioUser> findByUserUsername(final String username) {
        return find("user.username", username).firstResultOptional();
    }

}
