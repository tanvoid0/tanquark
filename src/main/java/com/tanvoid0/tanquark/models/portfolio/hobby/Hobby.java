package com.tanvoid0.tanquark.models.portfolio.hobby;

import com.tanvoid0.tanquark.common.base.BaseEntityOrdered;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import jakarta.persistence.*;
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
@Table(name = "user_hobbies")
@org.hibernate.annotations.NamedQuery(name = "Hobby.findAll", query = "select h from Hobby h order by h.orderSeq")
public class Hobby extends BaseEntityOrdered implements Serializable {

    @Serial
    private static final long serialVersionUID = -2559226959620398216L;

    @Column(nullable = false, unique = true)
    private String title;

    private String icon;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_user_id", nullable = false)
    private PortfolioUser portfolioUser;
}
