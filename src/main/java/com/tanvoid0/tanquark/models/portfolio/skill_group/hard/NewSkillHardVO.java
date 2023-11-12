package com.tanvoid0.tanquark.models.portfolio.skill_group.hard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewSkillHardVO {
    private String name;
    private List<NewSkillHardItemVO> items = new ArrayList<>();
}
