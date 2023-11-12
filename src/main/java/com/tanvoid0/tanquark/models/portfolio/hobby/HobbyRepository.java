package com.tanvoid0.tanquark.models.portfolio.hobby;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class HobbyRepository implements PanacheRepository<Hobby> {
    public List<Hobby> findAllByUserId(long userId) {
        return find("portfolioUser.id = :userId ORDER BY orderSeq", Collections.singletonMap("userId", userId)).list();
    }
}
