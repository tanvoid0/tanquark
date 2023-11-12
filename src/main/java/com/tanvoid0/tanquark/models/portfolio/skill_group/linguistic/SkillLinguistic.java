package com.tanvoid0.tanquark.models.portfolio.skill_group.linguistic;

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
@Table(name = "skill_linguistic")
public class SkillLinguistic extends BaseSkill implements Serializable {

    @Serial
    private static final long serialVersionUID = -5690787869821282001L;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", nullable = false)
    private SkillGroup skillGroup;
}
