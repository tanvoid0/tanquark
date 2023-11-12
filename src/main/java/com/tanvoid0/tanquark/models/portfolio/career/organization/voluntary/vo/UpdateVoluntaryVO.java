package com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.vo;

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
public class UpdateVoluntaryVO extends UpdateOrganizationVO {
    public static final String NAME = "Academic";
    @Serial
    private static final long serialVersionUID = -6770464054126803940L;

    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    private String role;
}
