package com.tanvoid0.tanquark.models.portfolio.social.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSocialVO {
    @JsonIgnore
    private long id;

    @NotNull
    private String title;

    @Nonnull
    private String url;

    private String icon;

    private String image;
}

