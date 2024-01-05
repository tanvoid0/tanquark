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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "portfolio_projects")
public class Project extends BaseEntityOrdered implements Serializable {

    @Serial
    private static final long serialVersionUID = 7770156024528025788L;

    @Column(nullable = false, unique = true)
    private String title;

    private String timeline;

    @Column(length = 3000)
    private String description;

    private String demo;
    private String source;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @JoinTable(name = "portfolio_project_platforms", joinColumns = @JoinColumn(name = "id"))
    private List<EPlatformType> platform = new ArrayList<>();

    @ElementCollection
    @JoinTable(name = "portfolio_project_tags", joinColumns = @JoinColumn(name = "id"))
    private List<String> tags = new ArrayList<>();


//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @OrderBy("orderSeq ASC")
//    private Set<ProjectFile> images = new HashSet<>();

    private String coverImage;

    @ElementCollection
    @JoinTable(name = "portfolio_project_images", joinColumns = @JoinColumn(name = "id"))
    private List<String> images = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private EProjectStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;
}
