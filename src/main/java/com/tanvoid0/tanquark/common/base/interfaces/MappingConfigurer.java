package com.tanvoid0.tanquark.common.base.interfaces;

import org.modelmapper.ModelMapper;

public interface MappingConfigurer<Entity, VO, NewVO> {
    void configure(ModelMapper mapper);

    Entity toEntity(NewVO newVO);

    VO toVO(Entity entity);

}
