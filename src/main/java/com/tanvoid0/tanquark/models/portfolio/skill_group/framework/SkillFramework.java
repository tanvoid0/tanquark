package com.tanvoid0.tanquark.models.portfolio.skill_group.framework;

import com.tanvoid0.tanquark.models.portfolio.skill_group.BaseSkill;
import com.tanvoid0.tanquark.models.portfolio.skill_group.SkillGroup;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(name = "skill_framework", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class SkillFramework extends BaseSkill implements Serializable {

    @Serial
    private static final long serialVersionUID = -4351519351228269323L;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name = "skill_framework_languages",
//            joinColumns = @JoinColumn(name = "framework_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "language_id", referencedColumnName = "id"))
//    private List<SkillLanguage> languages = new ArrayList<>();

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(
//            name = "skill_framework_libraries",
//            joinColumns = @JoinColumn(name = "framework_id"),
//            inverseJoinColumns = @JoinColumn(name = "library_id")
//    )
//    @OrderBy("orderSeq ASC")
//    private List<SkillLibrary> library = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlatformType platform;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_group_id", nullable = false)
    private SkillGroup skillGroup;
}
