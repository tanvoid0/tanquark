package com.tanvoid0.tanquark.models.portfolio.skill_group.hard;

import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;

@ApplicationScoped
public class SkillHardMapper {
    private final ModelMapper mapper = new ModelMapper();

    public SkillHard toEntity(final NewSkillHardVO newVO) {
        final SkillHard entity = mapper.map(newVO, SkillHard.class);
        entity.setItems(new ArrayList<>());
        return entity;
    }

    public SkillHardItem toEntity(final NewSkillHardItemVO newVO, final SkillHard parentEntity) {
        final SkillHardItem entity = mapper.map(newVO, SkillHardItem.class);
        entity.setSkill(parentEntity);
        return entity;
    }

    public SkillHardVO toVO(final SkillHard entity) {
        return mapper.map(entity, SkillHardVO.class);
    }

    public SkillHardItemVO toVO(final SkillHardItem entity) {
        return mapper.map(entity, SkillHardItemVO.class);
    }
}
