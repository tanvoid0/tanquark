package com.tanvoid0.tanquark.models.portfolio.hobby.vo;

import com.tanvoid0.tanquark.models.portfolio.hobby.Hobby;

public interface HobbyMapper {
    Hobby toEntity(NewHobbyVO newVO);

    void copy(UpdateHobbyVO updateVO, Hobby entity);

    HobbyVO toVO(Hobby entity);
}
