package com.tanvoid0.tanquark.models.portfolio.hobby;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class HobbyRepository implements PanacheRepository<Hobby> {
    public List<Hobby> findAllByUserId(long userId) {
        return find("portfolioUser.id = :userId ORDER BY orderSeq", Collections.singletonMap("userId", userId)).list();
    }

    public Optional<Hobby> findByUserIdAndTitle(long userId, String title) {
        return find("portfolioUser.id = :userId and title = :title ORDER BY orderSeq", Parameters.with("userId", userId).and("title", title)).stream().findFirst();
    }
}
