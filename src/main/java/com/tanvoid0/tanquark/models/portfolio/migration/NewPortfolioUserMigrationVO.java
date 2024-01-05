package com.tanvoid0.tanquark.models.portfolio.migration;

import com.tanvoid0.tanquark.models.portfolio.hobby.vo.NewHobbyVO;
import com.tanvoid0.tanquark.models.portfolio.social.vo.NewSocialVO;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@EqualsAndHashCode
public class NewPortfolioUserMigrationVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2508914240886913474L;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    @NotEmpty
    private String username;

    private String phone;

    private String avatar;

    private String address;

    private String fullName;

    private String coverImage;

    private short yob;

    private String title;

    private String titles;

    private String degree;

    private String whatIDo;

    private String about;

    private String aboutDetails;

    private String cvUrl;

    private String webUrl;

    private List<NewHobbyVO> hobbies;

    private List<NewSocialVO> socials;

    private NewCareerMigrationVO career;

    private NewPortfolioMigrationVO portfolio;

    private NewSkillMigrationVO skill;
}
