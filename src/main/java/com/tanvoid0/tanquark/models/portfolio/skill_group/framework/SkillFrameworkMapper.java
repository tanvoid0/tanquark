package com.tanvoid0.tanquark.models.portfolio.skill_group.framework;

import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class SkillFrameworkMapper {
    private final ModelMapper mapper = new ModelMapper();

    SkillFramework toEntity(final NewSkillFrameworkVO newVO) {
        return mapper.map(newVO, SkillFramework.class
        );
    }

    SkillFrameworkVO toVO(final SkillFramework entity) {
        return mapper.map(entity, SkillFrameworkVO.class);
    }
}
