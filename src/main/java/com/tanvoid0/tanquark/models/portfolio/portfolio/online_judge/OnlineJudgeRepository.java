package com.tanvoid0.tanquark.models.portfolio.portfolio.online_judge;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class OnlineJudgeRepository implements PanacheRepository<OnlineJudge> {

    public Optional<OnlineJudge> findByPortfolioAndName(final long portfolioId, final String name) {
        return find("portfolio.id = :portfolioId AND name = :name ORDER BY orderSeq", Parameters.with("portfolioId", portfolioId).and("name", name)).stream().findFirst();
    }
}
