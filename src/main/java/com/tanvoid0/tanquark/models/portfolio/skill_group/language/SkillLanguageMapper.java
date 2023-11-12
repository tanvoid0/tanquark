package com.tanvoid0.tanquark.models.portfolio.skill_group.language;

import com.tanvoid0.tanquark.models.portfolio.skill_group.hard.NewSkillHardItemVO;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class SkillLanguageMapper {
    private final ModelMapper mapper = new ModelMapper();

    public SkillLanguageVO toVO(final SkillLanguage entity) {
        return mapper.map(entity, SkillLanguageVO.class);
    }

    public SkillLanguage toEntity(final NewSkillHardItemVO vo) {
        return mapper.map(vo, SkillLanguage.class);
    }
}
