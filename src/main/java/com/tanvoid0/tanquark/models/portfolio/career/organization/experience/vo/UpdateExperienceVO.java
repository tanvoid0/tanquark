package com.tanvoid0.tanquark.models.portfolio.career.organization.experience.vo;

import com.tanvoid0.tanquark.models.portfolio.career.organization.UpdateOrganizationVO;
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
public class UpdateExperienceVO extends UpdateOrganizationVO {

    public static final String NAME = "UpdateExperience";
    @Serial
    private static final long serialVersionUID = 6067545221096958714L;
    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    @NotNull
    private String role;
}
