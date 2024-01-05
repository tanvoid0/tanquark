package com.tanvoid0.tanquark.models.portfolio.migration;

import com.tanvoid0.tanquark.models.portfolio.skill_group.framework.NewSkillFrameworkVO;
import com.tanvoid0.tanquark.models.portfolio.skill_group.hard.NewSkillHardItemVO;
import com.tanvoid0.tanquark.models.portfolio.skill_group.hard.NewSkillHardVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class NewSkillMigrationVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -8271318699031310686L;

    private List<NewSkillFrameworkVO> frameworks;

    private List<NewSkillHardVO> hardSkills;

    private List<NewSkillHardItemVO> languages;

    private List<NewSkillHardItemVO> linguisticSkills;

    private List<NewSkillHardItemVO> softSkills;
}
