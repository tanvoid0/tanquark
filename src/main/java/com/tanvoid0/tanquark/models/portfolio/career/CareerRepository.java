package com.tanvoid0.tanquark.models.portfolio.career;

import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class CareerRepository implements PanacheRepository<Career> {

    public Career findOrCreateByUser(final PortfolioUser portfolioUser) {
        final Optional<Career> career = find("portfolioUser", portfolioUser).firstResultOptional();
        if (career.isPresent()) {
            return career.get();
        } else {
            final Career newCareer = new Career();
            newCareer.setPortfolioUser(portfolioUser);
            newCareer.persist();
            return newCareer;
        }
    }
}
