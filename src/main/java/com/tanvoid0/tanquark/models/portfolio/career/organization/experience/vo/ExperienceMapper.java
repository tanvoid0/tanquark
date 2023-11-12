package com.tanvoid0.tanquark.models.portfolio.career.organization.experience.vo;

import com.tanvoid0.tanquark.common.base.interfaces.ModelMapperInterface;
import com.tanvoid0.tanquark.models.portfolio.career.organization.experience.Experience;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class ExperienceMapper implements ModelMapperInterface<Experience, ExperienceVO, NewExperienceVO, UpdateExperienceVO> {
    private ModelMapper mapper = new ModelMapper();

    @Override
    public Experience toEntity(NewExperienceVO newExperienceVO) {
        return mapper.map(newExperienceVO, Experience.class);
    }

    @Override
    public void copy(UpdateExperienceVO updateExperienceVO, Experience experience) {
        mapper.map(updateExperienceVO, experience);
    }

    @Override
    public ExperienceVO toVO(Experience experience) {
        return mapper.map(experience, ExperienceVO.class);
    }
}
