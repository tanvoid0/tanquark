package com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.vo;

import com.tanvoid0.tanquark.models.portfolio.career.Career;
import com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.Certificate;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class CertificateMapper {
    private ModelMapper mapper = new ModelMapper();

    public Certificate toEntity(NewCertificateVO newCertificateVO, final Career career) {
        final Certificate entity = mapper.map(newCertificateVO, Certificate.class);
        entity.setCareer(career);
        return entity;
    }

    public void copy(UpdateCertificateVO updateCertificateVO, Certificate certificate) {
        mapper.map(updateCertificateVO, certificate);
    }

    public CertificateVO toVO(Certificate certificate) {
        return mapper.map(certificate, CertificateVO.class);
    }
}
