package com.tanvoid0.tanquark.models.portfolio.portfolio;

import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class PortfolioRepository implements PanacheRepository<Portfolio> {

    public Portfolio findOrCreateByUser(final PortfolioUser portfolioUser) {
        final Optional<Portfolio> portfolio = find("portfolioUser", portfolioUser).firstResultOptional();

        if (portfolio.isPresent()) {
            return portfolio.get();
        } else {
            final Portfolio newPortfolio = new Portfolio();
            newPortfolio.setPortfolioUser(portfolioUser);
            newPortfolio.persist();
            return newPortfolio;
        }
    }
}
