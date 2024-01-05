package com.tanvoid0.tanquark.models.portfolio.migration;

import com.tanvoid0.tanquark.models.portfolio.career.organization.academic.vo.NewAcademicVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.vo.NewAchievementVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.vo.NewCertificateVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.experience.vo.NewExperienceVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.vo.NewVoluntaryVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class NewCareerMigrationVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6818293294269464404L;

    private List<NewAcademicVO> academics;

    private List<NewAchievementVO> achievements;

    private List<NewCertificateVO> certificates;

    private List<NewExperienceVO> experiences;

    private List<NewVoluntaryVO> voluntaries;
}
