package com.tanvoid0.tanquark.models.portfolio;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class NewPortfolioUserVO implements Serializable {
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

    private String about;

    private String aboutDetails;

    private String cvUrl;

    private String webUrl;
}
