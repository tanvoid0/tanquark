package com.tanvoid0.tanquark.models.portfolio.skill_group.language;

import com.tanvoid0.tanquark.common.base.BaseEntityOrdered;
import com.tanvoid0.tanquark.models.portfolio.skill_group.SkillGroup;
import jakarta.persistence.Column;
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
@Table(name = "skill_language")
public class SkillLanguage extends BaseEntityOrdered implements Serializable {
    @Serial
    private static final long serialVersionUID = -5562834924777077226L;

    @Column(nullable = false, unique = true)
    private String name;

    private String icon;
    private String image;

    private String fluency;
    private float fluencyVal;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", nullable = false)
    private SkillGroup skillGroup;

}
