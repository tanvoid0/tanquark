package com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.vo;

import com.tanvoid0.tanquark.models.portfolio.career.organization.UpdateOrganizationVO;
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
public class UpdateCertificateVO extends UpdateOrganizationVO {
    public static final String NAME = "UpdateCertificate";
    @Serial
    private static final long serialVersionUID = -9013699365899168029L;
    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    private String graduation;

    private String url;

    private String certificate;
}
