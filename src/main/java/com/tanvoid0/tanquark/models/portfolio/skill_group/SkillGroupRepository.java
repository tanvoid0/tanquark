package com.tanvoid0.tanquark.models.portfolio.skill_group;

import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class SkillGroupRepository implements PanacheRepository<SkillGroup> {

    public SkillGroup findByUser(final PortfolioUser portfolioUser) {
        final Optional<SkillGroup> skill = find("portfolioUser", portfolioUser).firstResultOptional();

        if (skill.isPresent()) {
            return skill.get();
        } else {
            final SkillGroup newSkillGroup = new SkillGroup();
            newSkillGroup.setPortfolioUser(portfolioUser);
            newSkillGroup.persist();
            return newSkillGroup;
        }
    }
}
