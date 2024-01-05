package com.tanvoid0.tanquark.models.portfolio.migration;

import com.tanvoid0.tanquark.models.portfolio.portfolio.online_judge.NewOnlineJudgeVO;
import com.tanvoid0.tanquark.models.portfolio.portfolio.project.NewProjectPortfolioVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class NewPortfolioMigrationVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3171168144805599734L;

    private List<NewOnlineJudgeVO> onlineJudges;

    private List<NewProjectPortfolioVO> projects;
}
