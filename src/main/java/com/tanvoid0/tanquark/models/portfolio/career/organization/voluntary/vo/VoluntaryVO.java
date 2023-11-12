package com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.vo;

import com.tanvoid0.tanquark.models.portfolio.career.organization.OrganizationVO;
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
public class VoluntaryVO extends OrganizationVO {
    public static final String NAME = "Volunteer";
    @Serial
    private static final long serialVersionUID = 1560321964971741272L;
    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    private String role;
}
