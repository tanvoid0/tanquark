package com.tanvoid0.tanquark.models.portfolio.skill_group.soft;

import com.tanvoid0.tanquark.models.portfolio.skill_group.BaseSkill;
import com.tanvoid0.tanquark.models.portfolio.skill_group.SkillGroup;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "skill_soft")
public class SkillSoft extends BaseSkill implements Serializable {

    @Serial
    private static final long serialVersionUID = 5885247111949774697L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_group_id", nullable = false)
    private SkillGroup skillGroup;
}
