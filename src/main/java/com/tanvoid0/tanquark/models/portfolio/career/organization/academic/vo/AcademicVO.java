package com.tanvoid0.tanquark.models.portfolio.career.organization.academic.vo;

import com.tanvoid0.tanquark.models.portfolio.career.organization.OrganizationVO;
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
public class AcademicVO extends OrganizationVO {
    public static final String NAME = "Academic";
    @Serial
    private static final long serialVersionUID = 8424509840083292339L;

    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    @NotNull
    private String graduation;
}