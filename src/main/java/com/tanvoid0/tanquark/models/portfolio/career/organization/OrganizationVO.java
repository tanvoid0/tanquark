package com.tanvoid0.tanquark.models.portfolio.career.organization;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.tanvoid0.tanquark.common.base.BaseOrderedVO;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "_type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AcademicVO.class, name = AcademicVO.NAME),
        @JsonSubTypes.Type(value = AchievementVO.class, name = AchievementVO.NAME),
        @JsonSubTypes.Type(value = CertificateVO.class, name = CertificateVO.NAME),
        @JsonSubTypes.Type(value = ExperienceVO.class, name = ExperienceVO.NAME),
        @JsonSubTypes.Type(value = VoluntaryVO.class, name = VoluntaryVO.NAME)
})
public abstract class OrganizationVO extends BaseOrderedVO {
    @Serial
    private static final long serialVersionUID = 1056798407230748074L;

    protected String _type;

    private String title;
    private String image;
    private String logo;
    private String institution;
    private String address;
    private String timeline;
    private String description;
    private String activities;
}
