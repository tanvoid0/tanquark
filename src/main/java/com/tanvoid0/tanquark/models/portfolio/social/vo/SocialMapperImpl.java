package com.tanvoid0.tanquark.models.portfolio.social.vo;

import com.tanvoid0.tanquark.models.portfolio.social.Social;
import org.modelmapper.ModelMapper;

public class SocialMapperImpl implements SocialMapper {
    ModelMapper mapper = new ModelMapper();

    @Override
    public Social toEntity(NewSocialVO newVO) {
        return mapper.map(newVO, Social.class);
    }

    @Override
    public void copy(UpdateSocialVO updateVO, Social entity) {
        mapper.map(updateVO, entity);
    }

    @Override
    public SocialVO toVO(Social entity) {
        return mapper.map(entity, SocialVO.class);
    }
}
