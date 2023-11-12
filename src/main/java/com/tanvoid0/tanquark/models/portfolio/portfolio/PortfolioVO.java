package com.tanvoid0.tanquark.models.portfolio.portfolio;

import com.tanvoid0.tanquark.common.base.BaseVO;
import com.tanvoid0.tanquark.models.portfolio.portfolio.online_judge_portfolio.OnlineJudgePortfolioVO;
import com.tanvoid0.tanquark.models.portfolio.portfolio.project_porfolio.ProjectPortfolioVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class PortfolioVO extends BaseVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6189903416500501747L;

    @Builder.Default
    private Set<OnlineJudgePortfolioVO> onlineJudges = new HashSet<>();

    @Builder.Default
    private Set<ProjectPortfolioVO> projects = new HashSet<>();
}
