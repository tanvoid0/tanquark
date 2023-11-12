package com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.vo;

import com.tanvoid0.tanquark.common.base.interfaces.ModelMapperInterface;
import com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.Voluntary;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class VoluntaryMapper implements ModelMapperInterface<Voluntary, VoluntaryVO, NewVoluntaryVO, UpdateVoluntaryVO> {
    private ModelMapper mapper = new ModelMapper();

    @Override
    public Voluntary toEntity(NewVoluntaryVO newVoluntaryVO) {
        return mapper.map(newVoluntaryVO, Voluntary.class);
    }

    @Override
    public void copy(UpdateVoluntaryVO updateVoluntaryVO, Voluntary voluntary) {
        mapper.map(updateVoluntaryVO, voluntary);
    }

    @Override
    public VoluntaryVO toVO(Voluntary voluntary) {
        return mapper.map(voluntary, VoluntaryVO.class);
    }
}
