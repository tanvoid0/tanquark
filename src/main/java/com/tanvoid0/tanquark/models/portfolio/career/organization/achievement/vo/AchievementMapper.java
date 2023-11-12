package com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.vo;

import com.tanvoid0.tanquark.common.base.interfaces.ModelMapperInterface;
import com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.Achievement;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class AchievementMapper implements ModelMapperInterface<Achievement, AchievementVO, NewAchievementVO, UpdateAchievementVO> {
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public Achievement toEntity(NewAchievementVO newAchievementVO) {
        return mapper.map(newAchievementVO, Achievement.class);
    }

    @Override
    public void copy(UpdateAchievementVO updateAchievementVO, Achievement achievement) {
        mapper.map(updateAchievementVO, achievement);
    }

    @Override
    public AchievementVO toVO(Achievement achievement) {
        return mapper.map(achievement, AchievementVO.class);
    }
}
