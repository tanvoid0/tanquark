package com.tanvoid0.tanquark.models.portfolio;

import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class PortfolioUserMapper {
    private ModelMapper mapper = new ModelMapper();

    public PortfolioUser toEntity(NewPortfolioUserVO newVO) {
        return mapper.map(newVO, PortfolioUser.class);
    }

    public PortfolioUserVO toVO(PortfolioUser entity) {
        final PortfolioUserVO vo = mapper.map(entity, PortfolioUserVO.class);
        vo.setName(entity.getUser().getName());
        vo.setEmail(entity.getUser().getEmail());
        vo.setUsername(entity.getUser().getUsername());
        vo.setPhone(entity.getUser().getPhone());
        return vo;
    }

    public PortfolioUserVO toSecretVO(final PortfolioUser entity) {
        final PortfolioUserVO vo = this.toVO(entity);
        vo.setFullName(vo.getName());
        vo.getCareer().getAchievements().clear();
        vo.setYob(null);
        vo.setPhone(null);
        vo.setPublicEmail(entity.getPublicEmail());
        return vo;
    }
}
