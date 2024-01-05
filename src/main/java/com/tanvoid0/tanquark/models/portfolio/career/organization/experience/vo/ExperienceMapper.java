package com.tanvoid0.tanquark.models.portfolio.career.organization.experience.vo;

import com.tanvoid0.tanquark.models.portfolio.career.Career;
import com.tanvoid0.tanquark.models.portfolio.career.organization.experience.Experience;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class ExperienceMapper {
    private ModelMapper mapper = new ModelMapper();

    public Experience toEntity(NewExperienceVO newExperienceVO, final Career career) {
        final Experience entity = mapper.map(newExperienceVO, Experience.class);
        entity.setCareer(career);
        return entity;
    }

    public void copy(UpdateExperienceVO updateExperienceVO, Experience experience) {
        mapper.map(updateExperienceVO, experience);
    }

    public ExperienceVO toVO(Experience experience) {
        return mapper.map(experience, ExperienceVO.class);
    }
}
