package com.tanvoid0.tanquark.common.base.interfaces;

public interface ModelMapperInterface<Entity, VO, NewVO, UpdateVO> {
    Entity toEntity(NewVO newVO);

    void copy(UpdateVO updateVO, Entity entity);

    VO toVO(Entity entity);
}
