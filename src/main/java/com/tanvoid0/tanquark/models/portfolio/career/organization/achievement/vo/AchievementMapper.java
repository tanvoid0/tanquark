package com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.vo;

import com.tanvoid0.tanquark.models.portfolio.career.Career;
import com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.Achievement;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class AchievementMapper {
    final ModelMapper mapper = new ModelMapper();

    public Achievement toEntity(NewAchievementVO newAchievementVO, final Career career) {
        final Achievement entity = mapper.map(newAchievementVO, Achievement.class);
        entity.setCareer(career);
        return entity;
    }

    public void copy(UpdateAchievementVO updateAchievementVO, Achievement achievement) {
        mapper.map(updateAchievementVO, achievement);
    }

    public AchievementVO toVO(Achievement achievement) {
        return mapper.map(achievement, AchievementVO.class);
    }
}
