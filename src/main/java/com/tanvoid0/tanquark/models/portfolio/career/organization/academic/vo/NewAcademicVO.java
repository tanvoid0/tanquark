package com.tanvoid0.tanquark.models.portfolio.career.organization.academic.vo;

import com.tanvoid0.tanquark.models.portfolio.career.organization.NewOrganizationVO;
import jakarta.validation.constraints.NotNull;
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
public class NewAcademicVO extends NewOrganizationVO {
    public static final String NAME = "NewAcademic";
    @Serial
    private static final long serialVersionUID = 7323763414092809293L;

    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    @NotNull
    private String graduation;
}

