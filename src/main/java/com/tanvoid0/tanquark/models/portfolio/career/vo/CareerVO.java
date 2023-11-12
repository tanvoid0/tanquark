package com.tanvoid0.tanquark.models.portfolio.career.vo;

import com.tanvoid0.tanquark.common.base.BaseVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.academic.vo.AcademicVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.vo.AchievementVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.vo.CertificateVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.experience.vo.ExperienceVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.vo.VoluntaryVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CareerVO extends BaseVO {
    @Serial
    private static final long serialVersionUID = -749568851377936027L;

    private List<AcademicVO> academics = new ArrayList<>();
    private List<AchievementVO> achievements = new ArrayList<>();
    private List<CertificateVO> certificates = new ArrayList<>();
    private List<ExperienceVO> experiences = new ArrayList<>();
    private List<VoluntaryVO> voluntaries = new ArrayList<>();
}