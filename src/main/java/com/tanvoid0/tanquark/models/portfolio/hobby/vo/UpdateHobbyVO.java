package com.tanvoid0.tanquark.models.portfolio.hobby.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateHobbyVO {
    @JsonIgnore
    private long id;

    @NotNull
    private String title;

    private String icon;

    private String image;
}

