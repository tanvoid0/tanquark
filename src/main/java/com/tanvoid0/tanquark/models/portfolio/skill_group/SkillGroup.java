package com.tanvoid0.tanquark.models.portfolio.skill_group;

import com.tanvoid0.tanquark.common.base.BaseEntity;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.skill_group.framework.SkillFramework;
import com.tanvoid0.tanquark.models.portfolio.skill_group.hard.SkillHard;
import com.tanvoid0.tanquark.models.portfolio.skill_group.language.SkillLanguage;
import com.tanvoid0.tanquark.models.portfolio.skill_group.library.SkillLibrary;
import com.tanvoid0.tanquark.models.portfolio.skill_group.linguistic.SkillLinguistic;
import com.tanvoid0.tanquark.models.portfolio.skill_group.soft.SkillSoft;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "skill_group")
public class SkillGroup extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1074405926970595615L;

    @OneToMany(mappedBy = "skillGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderSeq ASC")
    private List<SkillLanguage> languages;

    @OneToMany(mappedBy = "skillGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderSeq ASC")
    private List<SkillFramework> frameworks;

    @OneToMany(mappedBy = "skillGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderSeq ASC")
    private List<SkillLibrary> libraries;


    @OneToMany(mappedBy = "skillGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderSeq ASC")
    private List<SkillHard> hardSkills;

    @OneToMany(mappedBy = "skillGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderSeq ASC")
    private List<SkillSoft> softSkills;

    @OneToMany(mappedBy = "skillGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderSeq ASC")
    private List<SkillLinguistic> linguisticSkills;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_user_id", nullable = false)
    private PortfolioUser portfolioUser;

    public SkillGroup() {
        this.languages = new ArrayList<>();
        this.frameworks = new ArrayList<>();
        this.hardSkills = new ArrayList<>();
        this.softSkills = new ArrayList<>();
        this.linguisticSkills = new ArrayList<>();
    }
}
