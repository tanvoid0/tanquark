package com.tanvoid0.tanquark.models.portfolio.career.organization.academic.vo;

import com.tanvoid0.tanquark.models.portfolio.career.Career;
import com.tanvoid0.tanquark.models.portfolio.career.organization.academic.Academic;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class AcademicMapper {
    public final ModelMapper mapper = new ModelMapper();

    public Academic toEntity(NewAcademicVO newAcademicVO, final Career career) {
        final Academic entity = mapper.map(newAcademicVO, Academic.class);
        entity.setCareer(career);
        return entity;
    }

    public void copy(UpdateAcademicVO updateAcademicVO, Academic academic) {
        mapper.map(updateAcademicVO, academic);
    }

    public AcademicVO toVO(Academic academic) {
        return mapper.map(academic, AcademicVO.class);
    }
}
