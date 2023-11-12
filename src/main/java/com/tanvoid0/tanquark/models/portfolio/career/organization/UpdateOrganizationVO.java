package com.tanvoid0.tanquark.models.portfolio.career.organization;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.tanvoid0.tanquark.common.base.BaseVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.academic.vo.UpdateAcademicVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.vo.UpdateAchievementVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.vo.UpdateCertificateVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.experience.vo.UpdateExperienceVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.vo.UpdateVoluntaryVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "_type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UpdateAcademicVO.class, name = UpdateAcademicVO.NAME),
        @JsonSubTypes.Type(value = UpdateAchievementVO.class, name = UpdateAchievementVO.NAME),
        @JsonSubTypes.Type(value = UpdateCertificateVO.class, name = UpdateCertificateVO.NAME),
        @JsonSubTypes.Type(value = UpdateExperienceVO.class, name = UpdateExperienceVO.NAME),
        @JsonSubTypes.Type(value = UpdateVoluntaryVO.class, name = UpdateVoluntaryVO.NAME)
})
public abstract class UpdateOrganizationVO extends BaseVO {
    @Serial
    private static final long serialVersionUID = 2084868021803260984L;

    private Long id;
    private String title;

    private String image;
    private String logo;
    private String institution;
    private String address;
    private String timeline;
    private String description;
    private String activities;
}
