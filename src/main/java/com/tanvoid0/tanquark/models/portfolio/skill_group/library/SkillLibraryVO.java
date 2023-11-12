package com.tanvoid0.tanquark.models.portfolio.skill_group.library;

import com.tanvoid0.tanquark.common.base.BaseOrderedVO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SkillLibraryVO extends BaseOrderedVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 7220221576185093616L;

    private String name;
    private String category;
    private String icon;
    private String image;
    private String fluency;
    private float fluencyVal;
    private String description;
    private Long orderSeq;
}
