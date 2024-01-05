package com.tanvoid0.tanquark.models.portfolio.portfolio;

import com.tanvoid0.tanquark.common.base.BaseVO;
import com.tanvoid0.tanquark.models.portfolio.portfolio.online_judge.OnlineJudgeVO;
import com.tanvoid0.tanquark.models.portfolio.portfolio.project.ProjectPortfolioVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class PortfolioVO extends BaseVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6189903416500501747L;

    @Builder.Default
    private List<OnlineJudgeVO> onlineJudges = new ArrayList<>();

    @Builder.Default
    private List<ProjectPortfolioVO> projects = new ArrayList<>();
}
