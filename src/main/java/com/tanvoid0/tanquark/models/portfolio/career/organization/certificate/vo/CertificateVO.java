package com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.vo;

import com.tanvoid0.tanquark.models.portfolio.career.organization.OrganizationVO;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificateVO extends OrganizationVO {

    public static final String NAME = "Certificate";
    @Serial
    private static final long serialVersionUID = -7549642450492596228L;

    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    @NotNull
    private String graduation;

    @NotNull
    private String url;

    @NotNull
    private String certificate;
}
