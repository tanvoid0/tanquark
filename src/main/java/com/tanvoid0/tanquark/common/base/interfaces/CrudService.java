package com.tanvoid0.tanquark.common.base.interfaces;

import java.util.List;

public interface CrudService<Entity, VO, NewVO, UpdateVO> {

    List<VO> get();

    VO get(long id);

    VO add(NewVO newVO);

    List<VO> add(List<NewVO> newVOs);

    VO update(UpdateVO updateVO);

    void delete(long id);

    Entity findEntity(long id);

    VO convertEntityToVO(Entity entity);

    Entity convertVOToEntity(VO vo);
}
