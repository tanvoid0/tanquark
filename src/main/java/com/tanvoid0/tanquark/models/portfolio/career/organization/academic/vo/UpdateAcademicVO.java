package com.tanvoid0.tanquark.models.portfolio.career.organization.academic.vo;

import com.tanvoid0.tanquark.models.portfolio.career.organization.UpdateOrganizationVO;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Setter;

import java.io.Serial;

public class UpdateAcademicVO extends UpdateOrganizationVO {
    public static final String NAME = "UpdateAcademic";
    @Serial
    private static final long serialVersionUID = -4486095158664769748L;
    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    @NotNull
    private String graduation;
}
