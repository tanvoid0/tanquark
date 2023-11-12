package com.tanvoid0.tanquark.models.portfolio;

import com.tanvoid0.tanquark.common.base.BaseVO;
import com.tanvoid0.tanquark.models.portfolio.career.vo.CareerVO;
import com.tanvoid0.tanquark.models.portfolio.hobby.vo.HobbyVO;
import com.tanvoid0.tanquark.models.portfolio.skill_group.SkillGroupVO;
import com.tanvoid0.tanquark.models.portfolio.social.vo.SocialVO;
import com.tanvoid0.tanquark.models.user.UserVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PortfolioUserVO extends BaseVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8558878882117345174L;
    @Builder.Default
    private List<HobbyVO> hobbies = new ArrayList<>();
    @Builder.Default
    private List<SocialVO> socials = new ArrayList<>();
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
    private UserVO user;

    private CareerVO career;

    private SkillGroupVO skill;

//    private Portfolio portfolio;
}


