package com.tanvoid0.tanquark.models.portfolio.portfolio.online_judge_portfolio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOnlineJudgeVO {
    private String name;
    private String icon;
    private String image;
    private String progress;
    private String url;
}
