package com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.vo;

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
public class NewVoluntaryVO extends NewOrganizationVO {
    public static final String NAME = "NewVolunteer";
    @Serial
    private static final long serialVersionUID = 2418852920702036170L;

    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    private String role;
}
