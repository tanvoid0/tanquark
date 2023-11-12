package com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.vo;

import com.tanvoid0.tanquark.common.base.interfaces.ModelMapperInterface;
import com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.Certificate;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class CertificateMapper implements ModelMapperInterface<Certificate, CertificateVO, NewCertificateVO, UpdateCertificateVO> {
    private ModelMapper mapper = new ModelMapper();

    @Override
    public Certificate toEntity(NewCertificateVO newCertificateVO) {
        return mapper.map(newCertificateVO, Certificate.class);
    }

    @Override
    public void copy(UpdateCertificateVO updateCertificateVO, Certificate certificate) {
        mapper.map(updateCertificateVO, certificate);
    }

    @Override
    public CertificateVO toVO(Certificate certificate) {
        return mapper.map(certificate, CertificateVO.class);
    }
}
