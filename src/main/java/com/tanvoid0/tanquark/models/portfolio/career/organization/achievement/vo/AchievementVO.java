package com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.vo;

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
public class AchievementVO extends OrganizationVO {
    public static final String NAME = "Achievement";
    @Serial
    private static final long serialVersionUID = 194221696098663817L;

    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    @NotNull
    private String achievement;
}
