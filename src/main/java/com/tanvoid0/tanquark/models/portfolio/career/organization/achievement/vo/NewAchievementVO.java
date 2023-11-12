package com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.vo;

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
public class NewAchievementVO extends NewOrganizationVO {
    public static final String NAME = "NewAchievement";
    @Serial
    private static final long serialVersionUID = -2710597141004994490L;

    @Setter(AccessLevel.NONE)
    private final String _type = NAME;

    @NotNull
    private String achievement;
}

