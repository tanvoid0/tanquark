package com.tanvoid0.tanquark.models.portfolio.social.vo;

import com.tanvoid0.tanquark.models.portfolio.social.Social;

public interface SocialMapper {
    Social toEntity(NewSocialVO newVO);

    void copy(UpdateSocialVO updateVO, Social entity);

    SocialVO toVO(Social entity);
}
