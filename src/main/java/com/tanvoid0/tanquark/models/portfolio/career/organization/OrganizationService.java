package com.tanvoid0.tanquark.models.portfolio.career.organization;

import com.tanvoid0.tanquark.config.auth.AuthService;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.career.Career;
import com.tanvoid0.tanquark.models.portfolio.career.CareerRepository;
import com.tanvoid0.tanquark.models.portfolio.career.organization.academic.Academic;
import com.tanvoid0.tanquark.models.portfolio.career.organization.academic.vo.AcademicMapper;
import com.tanvoid0.tanquark.models.portfolio.career.organization.academic.vo.AcademicVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.academic.vo.NewAcademicVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.Achievement;
import com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.vo.AchievementMapper;
import com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.vo.AchievementVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.vo.NewAchievementVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.Certificate;
import com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.vo.CertificateMapper;
import com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.vo.CertificateVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.vo.NewCertificateVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.experience.Experience;
import com.tanvoid0.tanquark.models.portfolio.career.organization.experience.vo.ExperienceMapper;
import com.tanvoid0.tanquark.models.portfolio.career.organization.experience.vo.ExperienceVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.experience.vo.NewExperienceVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.Voluntary;
import com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.vo.NewVoluntaryVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.vo.VoluntaryMapper;
import com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.vo.VoluntaryVO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Transactional
@ApplicationScoped
@RequiredArgsConstructor
public class OrganizationService {

    private final AuthService authService;

    private final CareerRepository careerRepository;
    private final OrganizationValidator validator;
    private final AcademicMapper academicMapper;
    private final AchievementMapper achievementMapper;
    private final CertificateMapper certificateMapper;
    private final ExperienceMapper experienceMapper;
    private final VoluntaryMapper voluntaryMapper;

    public List<AcademicVO> getAcademics() {
        final Career career = getCareerForCurrentUser();

        return career.getAcademics().stream().map(academicMapper::toVO).toList();
    }

    public List<AchievementVO> getAchievements() {
        final Career career = getCareerForCurrentUser();
        return career.getAchievements().stream().map(achievementMapper::toVO).toList();
    }

    public List<CertificateVO> getCertificates() {
        return getCareerForCurrentUser().getCertificates().stream().map(certificateMapper::toVO).toList();
    }

    public List<ExperienceVO> getExperiences() {
        return getCareerForCurrentUser().getExperiences().stream().map(experienceMapper::toVO).toList();
    }

    public List<VoluntaryVO> getVoluntaries() {
        return getCareerForCurrentUser().getVoluntaries().stream().map(voluntaryMapper::toVO).toList();
    }

    public List<AcademicVO> createAcademicBatch(final List<NewAcademicVO> request) {
        return request.stream().map(this::createAcademic).toList();
    }

    public AcademicVO createAcademic(final NewAcademicVO request) {
        final Career career = getCareerForCurrentUser();
        validator.validate(request);
        final Academic entity = academicMapper.toEntity(request);
        entity.setCareer(career);
        entity.persist();

        return academicMapper.toVO(entity);
    }

    public List<AchievementVO> createAchievementBatch(final List<NewAchievementVO> request) {
        return request.stream().map(this::createAchievement).toList();
    }

    public AchievementVO createAchievement(final NewAchievementVO request) {
        final Career career = getCareerForCurrentUser();
        validator.validate(request);
        final Achievement entity = achievementMapper.toEntity(request);
        entity.setCareer(career);
        entity.persist();

        return achievementMapper.toVO(entity);
    }

    public List<CertificateVO> createCertificateBatch(final List<NewCertificateVO> request) {
        return request.stream().map(this::createCertificate).toList();
    }

    public CertificateVO createCertificate(final NewCertificateVO request) {
        final Career career = getCareerForCurrentUser();
        validator.validate(request);
        final Certificate entity = certificateMapper.toEntity(request);
        entity.setCareer(career);
        entity.persist();

        return certificateMapper.toVO(entity);
    }

    public List<ExperienceVO> createExperienceBatch(final List<NewExperienceVO> request) {
        return request.stream().map(this::createExperience).toList();
    }

    public ExperienceVO createExperience(final NewExperienceVO request) {
        final Career career = getCareerForCurrentUser();
        validator.validate(request);
        final Experience entity = experienceMapper.toEntity(request);
        entity.setCareer(career);
        entity.persist();

        return experienceMapper.toVO(entity);
    }

    public List<VoluntaryVO> createVoluntaryBatch(final List<NewVoluntaryVO> request) {
        return request.stream().map(this::createVoluntary).toList();
    }

    public VoluntaryVO createVoluntary(final NewVoluntaryVO request) {
        final Career career = getCareerForCurrentUser();
        validator.validate(request);
        final Voluntary entity = voluntaryMapper.toEntity(request);
        entity.setCareer(career);
        entity.persist();

        return voluntaryMapper.toVO(entity);
    }

    private Career getCareerForCurrentUser() {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();
        return careerRepository.findByUser(user);
    }
}
