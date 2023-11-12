package com.tanvoid0.tanquark.models.portfolio.hobby.vo;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class NewHobbyVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4506597663902264079L;

    @Nonnull
    private String title;

    private String icon;

    private String image;
}
