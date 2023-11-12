package com.tanvoid0.tanquark.models.portfolio.social;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class SocialRepository implements PanacheRepository<Social> {
    public List<Social> findAllByUserId(long userId) {
        return find("portfolioUser.id = :userId ORDER BY orderSeq", Collections.singletonMap("userId", userId)).list();
    }
}
