package com.tanvoid0.tanquark.models.portfolio.portfolio_contact_request;

import com.tanvoid0.tanquark.common.base.BaseEntity;
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
@Table(name = "portfolio_contact_request")
public class PortfolioContactRequest extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 6082227505428447362L;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String subject;

    @Column(length = 3000)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_user_id", nullable = false)
    private PortfolioUser portfolioUser;
}
