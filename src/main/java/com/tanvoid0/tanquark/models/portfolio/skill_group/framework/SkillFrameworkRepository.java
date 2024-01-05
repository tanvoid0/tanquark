package com.tanvoid0.tanquark.models.portfolio.skill_group.framework;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped

public class SkillFrameworkRepository implements PanacheRepository<SkillFramework> {

    public Optional<SkillFramework> findBySkillGroupAndName(final long skillGroupId, final String name) {
        return find("skillGroup.id = :skillGroupId AND name = :name ORDER BY orderSeq", Parameters.with("skillGroupId", skillGroupId).and("name", name)).stream().findFirst();
    }
}
