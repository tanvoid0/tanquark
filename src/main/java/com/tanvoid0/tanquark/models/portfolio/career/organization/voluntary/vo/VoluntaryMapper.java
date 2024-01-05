package com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.vo;

import com.tanvoid0.tanquark.models.portfolio.career.Career;
import com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.Voluntary;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class VoluntaryMapper {
    private ModelMapper mapper = new ModelMapper();

    public Voluntary toEntity(final NewVoluntaryVO newVoluntaryVO, final Career career) {
        final Voluntary entity = mapper.map(newVoluntaryVO, Voluntary.class);
        entity.setCareer(career);
        return entity;
    }

    public void copy(UpdateVoluntaryVO updateVoluntaryVO, Voluntary voluntary) {
        mapper.map(updateVoluntaryVO, voluntary);
    }

    public VoluntaryVO toVO(Voluntary voluntary) {
        return mapper.map(voluntary, VoluntaryVO.class);
    }
}
