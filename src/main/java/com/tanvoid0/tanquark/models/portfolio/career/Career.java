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
import java.util.ArrayList;
import java.util.List;

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
    private List<Academic> academics;

    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = false)
    @OrderBy("orderSeq ASC")
    private List<Achievement> achievements;

    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = false)
    @OrderBy("orderSeq ASC")
    private List<Certificate> certificates;

    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = false)
    @OrderBy("orderSeq ASC")
    private List<Experience> experiences;

    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = false)
    @OrderBy("orderSeq ASC")
    private List<Voluntary> voluntaries;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_user_id", nullable = false)
    private PortfolioUser portfolioUser;

    public Career() {
        academics = new ArrayList<>();
        achievements = new ArrayList<>();
        certificates = new ArrayList<>();
        experiences = new ArrayList<>();
        voluntaries = new ArrayList<>();
    }
}