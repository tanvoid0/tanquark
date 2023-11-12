package com.tanvoid0.tanquark.models.portfolio.skill_group.hard;

import com.tanvoid0.tanquark.common.base.BaseOrderedVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkillHardVO extends BaseOrderedVO {
    @Serial
    private static final long serialVersionUID = -4158720976065280649L;

    private String name;
    private List<SkillHardItemVO> items = new ArrayList<>();
}
