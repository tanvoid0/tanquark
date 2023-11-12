package com.tanvoid0.tanquark.models.portfolio.skill_group.soft;

import com.tanvoid0.tanquark.common.base.BaseOrderedVO;
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
public class SkillSoftVO extends BaseOrderedVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 178654979987137778L;

    private String name;
    private String category;
    private String icon;
    private String image;
    private String fluency;
    private float fluencyVal;
    private String description;
}
