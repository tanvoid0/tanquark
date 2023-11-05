package com.tanvoid0.tanquark.models.portfolio;


import com.tanvoid0.tanquark.common.base.BaseEntity;
import com.tanvoid0.tanquark.models.portfolio.career.Career;
import com.tanvoid0.tanquark.models.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "portfolio_user")
public class PortfolioUser extends BaseEntity implements Serializable {
    private String avatar;

    private String address;

    private String fullName;

    private String coverImage;

    private short yob;

    private String title;

    private String titles;

    private String degree;

    private String whatIDo;

    @Column(length = 3000)
    private String about;

    @Column(length = 3000)
    private String aboutDetails;

    private String cvUrl;

    private String webUrl;

    @OneToOne
    private User user;

    @OneToOne(mappedBy = "portfolio_user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Career career;
}
