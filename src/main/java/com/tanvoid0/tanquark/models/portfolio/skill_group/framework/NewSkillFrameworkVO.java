package com.tanvoid0.tanquark.models.portfolio.skill_group.framework;

import com.tanvoid0.tanquark.models.portfolio.skill_group.NewSkillVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class NewSkillFrameworkVO extends NewSkillVO {
    private PlatformType platform;
}
