package com.tanvoid0.tanquark.models.portfolio.career;

import com.tanvoid0.tanquark.common.base.BaseEntity;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.career.organization.academic.Academic;
import com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.Achievement;
import com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.Certificate;
import com.tanvoid0.tanquark.models.portfolio.career.organization.experience.Experience;
import com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.Voluntary;
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
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "user_career")
public class Career extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 4294294701060916909L;

    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = false)
    @OrderBy("orderSeq ASC")
    private Set<Academic> academics;

    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = false)
    @OrderBy("orderSeq ASC")
    private Set<Achievement> achievements;

    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = false)
    @OrderBy("orderSeq ASC")
    private Set<Certificate> certificates;

    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = false)
    @OrderBy("orderSeq ASC")
    private Set<Experience> experiences;

    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = false)
    @OrderBy("orderSeq ASC")
    private Set<Voluntary> voluntaries;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_user_id", nullable = false)
    private PortfolioUser portfolio_user;

    public Career() {
        academics = new HashSet<>();
        achievements = new HashSet<>();
        certificates = new HashSet<>();
        experiences = new HashSet<>();
        voluntaries = new HashSet<>();
    }
}