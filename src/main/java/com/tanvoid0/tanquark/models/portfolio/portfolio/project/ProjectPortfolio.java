package com.tanvoid0.tanquark.models.portfolio.portfolio.project;

import com.tanvoid0.tanquark.common.base.BaseEntityOrdered;
import com.tanvoid0.tanquark.models.portfolio.portfolio.Portfolio;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "portfolio_project")
public class ProjectPortfolio extends BaseEntityOrdered implements Serializable {

    @Serial
    private static final long serialVersionUID = 7770156024528025788L;

    @Column(nullable = false, unique = true)
    private String title;

    private String timeline;

    @Column(length = 3000)
    private String description;

    private String demo;
    private String source;

    //    @Enumerated(EnumType.STRING)
//    @Convert(converter = PlatformTypeEnumSetConverter.class)
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<EPlatformType> platform = new HashSet<>();

    @ElementCollection
    private Set<String> tags = new HashSet<>();


//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @OrderBy("orderSeq ASC")
//    private Set<ProjectFile> images = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private EProjectStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;
}
