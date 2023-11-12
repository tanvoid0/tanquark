package com.tanvoid0.tanquark.models.portfolio.skill_group.linguistic;

import com.tanvoid0.tanquark.models.portfolio.skill_group.hard.NewSkillHardItemVO;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class SkillLinguisticMapper {

    private final ModelMapper mapper = new ModelMapper();

    public SkillLinguisticVO toVO(final SkillLinguistic entity) {
        return mapper.map(entity, SkillLinguisticVO.class);
    }

    public SkillLinguistic toEntity(final NewSkillHardItemVO newVO) {
        return mapper.map(newVO, SkillLinguistic.class);
    }
}
