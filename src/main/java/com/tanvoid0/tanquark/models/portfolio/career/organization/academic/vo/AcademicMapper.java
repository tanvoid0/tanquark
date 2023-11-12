package com.tanvoid0.tanquark.models.portfolio.career.organization.academic.vo;

import com.tanvoid0.tanquark.common.base.interfaces.ModelMapperInterface;
import com.tanvoid0.tanquark.models.portfolio.career.organization.academic.Academic;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class AcademicMapper implements ModelMapperInterface<Academic, AcademicVO, NewAcademicVO, UpdateAcademicVO> {
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public Academic toEntity(NewAcademicVO newAcademicVO) {
        return mapper.map(newAcademicVO, Academic.class);
    }

    @Override
    public void copy(UpdateAcademicVO updateAcademicVO, Academic academic) {
        mapper.map(updateAcademicVO, academic);
    }

    @Override
    public AcademicVO toVO(Academic academic) {
        return mapper.map(academic, AcademicVO.class);
    }
}
