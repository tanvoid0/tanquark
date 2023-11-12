package com.tanvoid0.tanquark.models.portfolio.portfolio.online_judge_portfolio;

import com.tanvoid0.tanquark.common.base.BaseEntityOrdered;
import com.tanvoid0.tanquark.models.portfolio.portfolio.Portfolio;
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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "portfolio_online_judges")
public class OnlineJudgePortfolio extends BaseEntityOrdered implements Serializable {

    @Serial
    private static final long serialVersionUID = 3096596783107258500L;

    @Column(nullable = false)
    private String name;

    private String icon;

    private String image;

    @Column(nullable = false)
    private String progress;

    @Column(nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;
}
