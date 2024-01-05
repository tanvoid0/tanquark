package com.tanvoid0.tanquark.models.portfolio.skill_group.framework;

import com.tanvoid0.tanquark.models.portfolio.skill_group.SkillGroup;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class SkillFrameworkMapper {
    private final ModelMapper mapper = new ModelMapper();

    SkillFramework toEntity(final NewSkillFrameworkVO newVO, final SkillGroup skillGroup) {
        final SkillFramework entity = mapper.map(newVO, SkillFramework.class
        );
        entity.setSkillGroup(skillGroup);
        return entity;
    }

    SkillFrameworkVO toVO(final SkillFramework entity) {
        return mapper.map(entity, SkillFrameworkVO.class);
    }
}
