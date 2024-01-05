package com.tanvoid0.tanquark.models.portfolio.skill_group.language;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class SkillLanguageRepository implements PanacheRepository<SkillLanguage> {
    public Optional<SkillLanguage> findBySkillGroupAndName(final long skillGroupId, final String name) {
        return find("skillGroup.id = :skillGroupId AND name = :name ORDER BY orderSeq", Parameters.with("skillGroupId", skillGroupId).and("name", name)).stream().findFirst();
    }
}
