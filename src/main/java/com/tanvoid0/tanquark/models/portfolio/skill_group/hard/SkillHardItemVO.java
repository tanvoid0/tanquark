package com.tanvoid0.tanquark.models.portfolio.skill_group.hard;

import com.tanvoid0.tanquark.models.portfolio.skill_group.BaseSkillVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkillHardItemVO extends BaseSkillVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4982497023585950056L;

    private Long orderSeq;
}
