package com.tanvoid0.tanquark.models.portfolio.social;

import com.tanvoid0.tanquark.common.base.BaseEntityOrdered;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
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
@Table(name = "user_socials")
@org.hibernate.annotations.NamedQuery(name = "Social.findAll", query = "select h from Hobby h order by h.orderSeq")
public class Social extends BaseEntityOrdered implements Serializable {
    @Serial
    private static final long serialVersionUID = -9177368008748000372L;
    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, unique = true)
    private String url;

    private String image;

    private String icon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_user_id", nullable = false)
    private PortfolioUser portfolioUser;
}
