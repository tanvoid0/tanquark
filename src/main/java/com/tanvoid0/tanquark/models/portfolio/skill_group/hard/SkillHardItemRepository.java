package com.tanvoid0.tanquark.models.portfolio.skill_group.hard;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class SkillHardItemRepository implements PanacheRepository<SkillHardItem> {

    public Optional<SkillHardItem> findBySkillHardAndName(final long skillId, final String name) {
        return find("skill.id = :skillId AND name = :name ORDER BY orderSeq", Parameters.with("skillId", skillId).and("name", name)).stream().findFirst();
    }
}
