package com.tanvoid0.tanquark.models.portfolio.career.organization.experience;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ExperienceRepository implements PanacheRepository<Experience> {

    public Optional<Experience> findByCareerIdIdAndTitleAndInstitution(final long careerId, final String title, final String institution) {
        return find("career.id = :careerId AND title = :title AND institution = :institution ORDER BY orderSeq", Parameters
                .with("careerId", careerId)
                .and("title", title)
                .and("institution", institution)
        ).stream().findFirst();
    }
}
