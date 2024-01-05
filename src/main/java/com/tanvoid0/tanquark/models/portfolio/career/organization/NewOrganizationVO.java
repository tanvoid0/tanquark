package com.tanvoid0.tanquark.models.portfolio.career.organization;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.tanvoid0.tanquark.models.portfolio.career.organization.academic.vo.NewAcademicVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.vo.NewAchievementVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.vo.NewCertificateVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.experience.vo.NewExperienceVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.vo.NewVoluntaryVO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

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
        @JsonSubTypes.Type(value = NewAcademicVO.class, name = NewAcademicVO.NAME),
        @JsonSubTypes.Type(value = NewAchievementVO.class, name = NewAchievementVO.NAME),
        @JsonSubTypes.Type(value = NewCertificateVO.class, name = NewCertificateVO.NAME),
        @JsonSubTypes.Type(value = NewExperienceVO.class, name = NewExperienceVO.NAME),
        @JsonSubTypes.Type(value = NewVoluntaryVO.class, name = NewVoluntaryVO.NAME)
})
public abstract class NewOrganizationVO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 418911591761821699L;

    @NotNull
    private String title;

    private String image;
    private String logo;
    private String institution;
    private String address;
    private String timeline;
    private String description;
    private String activities;
}
