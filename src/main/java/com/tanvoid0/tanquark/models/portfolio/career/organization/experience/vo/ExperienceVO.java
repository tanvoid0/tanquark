package com.tanvoid0.tanquark.models.portfolio.career.organization.experience.vo;

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
public class ExperienceVO extends OrganizationVO {
    public static final String NAME = "Experience";
    @Serial
    private static final long serialVersionUID = 504721862153992265L;

    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    @NotNull
    private String role;
}
