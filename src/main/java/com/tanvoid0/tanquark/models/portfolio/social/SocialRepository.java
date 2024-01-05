package com.tanvoid0.tanquark.models.portfolio.social;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SocialRepository implements PanacheRepository<Social> {
    public List<Social> findAllByUserId(long userId) {
        return find("portfolioUser.id = :userId ORDER BY orderSeq", Collections.singletonMap("userId", userId)).list();
    }

    public Optional<Social> findByUserIdAndTitle(long userId, String title) {
        return find("portfolioUser.id = :userId and title = :title ORDER BY orderSeq", Parameters.with("userId", userId).and("title", title)).stream().findFirst();
    }
}
