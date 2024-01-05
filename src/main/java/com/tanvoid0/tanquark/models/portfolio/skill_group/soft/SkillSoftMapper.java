package com.tanvoid0.tanquark.models.portfolio.skill_group.soft;

import com.tanvoid0.tanquark.models.portfolio.skill_group.SkillGroup;
import com.tanvoid0.tanquark.models.portfolio.skill_group.hard.NewSkillHardItemVO;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class SkillSoftMapper {

    private final ModelMapper mapper = new ModelMapper();

    public SkillSoftVO toVO(final SkillSoft entity) {
        return mapper.map(entity, SkillSoftVO.class);
    }

    public SkillSoft toEntity(final NewSkillHardItemVO newVO, final SkillGroup skillGroup) {
        final SkillSoft entity = mapper.map(newVO, SkillSoft.class);
        entity.setSkillGroup(skillGroup);
        return entity;
    }
}
