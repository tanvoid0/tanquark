package com.tanvoid0.tanquark.models.portfolio.skill_group;

import com.tanvoid0.tanquark.models.portfolio.skill_group.framework.SkillFrameworkVO;
import com.tanvoid0.tanquark.models.portfolio.skill_group.hard.SkillHardVO;
import com.tanvoid0.tanquark.models.portfolio.skill_group.language.SkillLanguageVO;
import com.tanvoid0.tanquark.models.portfolio.skill_group.linguistic.SkillLinguisticVO;
import com.tanvoid0.tanquark.models.portfolio.skill_group.soft.SkillSoftVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillGroupVO {
    private Long id;
    private List<SkillLanguageVO> languages = new ArrayList<>();
    private List<SkillFrameworkVO> frameworks = new ArrayList<>();
    private List<SkillHardVO> hardSkills = new ArrayList<>();
    private List<SkillSoftVO> softSkills = new ArrayList<>();
    private List<SkillLinguisticVO> linguisticSkills = new ArrayList<>();
}
