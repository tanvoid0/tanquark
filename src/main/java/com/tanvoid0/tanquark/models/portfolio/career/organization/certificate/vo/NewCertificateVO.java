package com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.vo;

import com.tanvoid0.tanquark.models.portfolio.career.organization.NewOrganizationVO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewCertificateVO extends NewOrganizationVO {

    public static final String NAME = "NewCertificate";
    @Serial
    private static final long serialVersionUID = 5842249844052209564L;

    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    private String graduation;

    private String url;

    private String certificate;
}
