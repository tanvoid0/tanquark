package com.tanvoid0.tanquark.models.portfolio.social.vo;

import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.social.Social;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class SocialMapper {
    public ModelMapper mapper = new ModelMapper();

    public Social toEntity(NewSocialVO newVO, final PortfolioUser user) {
        final Social entity = mapper.map(newVO, Social.class);
        entity.setPortfolioUser(user);
        return entity;
    }

    public void copy(UpdateSocialVO updateVO, Social entity) {
        mapper.map(updateVO, entity);
    }

    public SocialVO toVO(Social entity) {
        return mapper.map(entity, SocialVO.class);
    }
}
