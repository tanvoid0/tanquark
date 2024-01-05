package com.tanvoid0.tanquark.models.portfolio.hobby.vo;

import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.hobby.Hobby;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class HobbyMapper {
    public ModelMapper mapper = new ModelMapper();

    public Hobby toEntity(final NewHobbyVO newVO, final PortfolioUser user) {
        final Hobby entity = mapper.map(newVO, Hobby.class);
        entity.setPortfolioUser(user);
        return entity;
    }

    public void copy(UpdateHobbyVO updateVO, Hobby entity) {
        mapper.map(updateVO, entity);
    }

    public HobbyVO toVO(Hobby entity) {
        return mapper.map(entity, HobbyVO.class);
    }
}
