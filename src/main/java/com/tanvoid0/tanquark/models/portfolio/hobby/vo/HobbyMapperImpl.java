package com.tanvoid0.tanquark.models.portfolio.hobby.vo;

import com.tanvoid0.tanquark.models.portfolio.hobby.Hobby;
import org.modelmapper.ModelMapper;

public class HobbyMapperImpl implements HobbyMapper {
    ModelMapper mapper = new ModelMapper();

    @Override
    public Hobby toEntity(NewHobbyVO newVO) {
        return mapper.map(newVO, Hobby.class);
    }

    @Override
    public void copy(UpdateHobbyVO updateVO, Hobby entity) {
        mapper.map(updateVO, entity);
    }

    @Override
    public HobbyVO toVO(Hobby entity) {
        return mapper.map(entity, HobbyVO.class);
    }
}
