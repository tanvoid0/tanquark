package com.tanvoid0.tanquark.models.portfolio.portfolio.project;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ProjectPortfolioRepository implements PanacheRepository<Project> {

    public Optional<Project> findByPortfolioAndTitle(final long portfolioId, final String title) {
        return find("portfolio.id = :portfolioId AND title = :title ORDER BY orderSeq", Parameters.with("portfolioId", portfolioId).and("title", title)).stream().findFirst();
    }
}
