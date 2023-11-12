package com.tanvoid0.tanquark.models.portfolio.portfolio;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PortfolioRepository implements PanacheRepository<Portfolio> {
}
