package com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.vo;

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
public class UpdateAchievementVO extends UpdateOrganizationVO {
    public static final String NAME = "UpdateAchievement";
    @Serial
    private static final long serialVersionUID = 1377109399118181050L;
    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    @NotNull
    private String achievement;
}
