package com.tanvoid0.tanquark.models.portfolio.portfolio.online_judge;

import com.tanvoid0.tanquark.common.base.BaseOrderedVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OnlineJudgeVO extends BaseOrderedVO {
    @Serial
    private static final long serialVersionUID = 7033134846304868407L;

    private String name;
    private String icon;
    private String image;
    private String progress;
    private String url;
}
