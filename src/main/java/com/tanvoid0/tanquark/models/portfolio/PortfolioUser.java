package com.tanvoid0.tanquark.models.portfolio;


import com.tanvoid0.tanquark.common.base.BaseEntity;
import com.tanvoid0.tanquark.models.portfolio.career.Career;
import com.tanvoid0.tanquark.models.portfolio.hobby.Hobby;
import com.tanvoid0.tanquark.models.portfolio.portfolio.Portfolio;
import com.tanvoid0.tanquark.models.portfolio.skill_group.SkillGroup;
import com.tanvoid0.tanquark.models.portfolio.social.Social;
import com.tanvoid0.tanquark.models.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "portfolio_user")
public class PortfolioUser extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -4475019375496753602L;

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

    @OneToOne(cascade = CascadeType.REMOVE)
    private User user;

    @OneToMany(mappedBy = "portfolioUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderSeq")
    private List<Hobby> hobbies = new ArrayList<>();

    @OneToMany(mappedBy = "portfolioUser", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderSeq ASC")
    private Set<Social> socials = new HashSet<>();

    @OneToOne(mappedBy = "portfolioUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Career career;

    @OneToOne(mappedBy = "portfolioUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Portfolio portfolio;

    @OneToOne(mappedBy = "portfolioUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private SkillGroup skill;
}
