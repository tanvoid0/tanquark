package com.tanvoid0.tanquark.models.portfolio.career.organization.academic;

import com.tanvoid0.tanquark.models.portfolio.career.organization.academic.Academic;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class AcademicRepository implements PanacheRepository<Academic> {

    public Optional<Academic> findByCareerIdIdAndTitleAndInstitution(final long careerId, final String title, final String institution) {
        return find("career.id = :careerId AND title = :title AND institution = :institution ORDER BY orderSeq", Parameters
                .with("careerId", careerId)
                .and("title", title)
                .and("institution", institution)
        ).stream().findFirst();
    }
}