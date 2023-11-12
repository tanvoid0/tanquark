package com.tanvoid0.tanquark.models.portfolio.social.vo;

import com.tanvoid0.tanquark.common.base.BaseVO;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SocialVO extends BaseVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -970951687224121177L;

    @Nonnull
    private String title;

    @Nonnull
    private String url;

    private String icon;

    private Long orderSeq;
}
