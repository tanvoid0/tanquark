package com.tanvoid0.tanquark.models.portfolio.portfolio;

import com.tanvoid0.tanquark.common.base.BaseEntity;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.portfolio.online_judge_portfolio.OnlineJudgePortfolio;
import com.tanvoid0.tanquark.models.portfolio.portfolio.project.ProjectPortfolio;
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
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_portfolio")
public class Portfolio extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -4955820939834700439L;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderSeq ASC")
    private Set<OnlineJudgePortfolio> onlineJudges = new HashSet<>();

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderSeq ASC")
    private Set<ProjectPortfolio> projects = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_user_id", nullable = false)
    private PortfolioUser portfolioUser;
}
