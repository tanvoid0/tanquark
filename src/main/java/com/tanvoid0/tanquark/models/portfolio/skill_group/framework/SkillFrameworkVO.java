package com.tanvoid0.tanquark.models.portfolio.skill_group.framework;

import com.tanvoid0.tanquark.models.portfolio.skill_group.BaseSkillVO;
import com.tanvoid0.tanquark.models.portfolio.skill_group.language.SkillLanguageVO;
import com.tanvoid0.tanquark.models.portfolio.skill_group.library.SkillLibraryVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SkillFrameworkVO extends BaseSkillVO {
    @Serial
    private static final long serialVersionUID = 4660457935960758222L;

    private List<SkillLanguageVO> languages = new ArrayList<>();
    private List<SkillLibraryVO> libraries = new ArrayList<>();
    private PlatformType platform = PlatformType.UNCATEGORIZED;
}
