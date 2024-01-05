package com.tanvoid0.tanquark.models.portfolio.career.organization;

import com.tanvoid0.tanquark.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanquark.config.auth.AuthService;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUserRepository;
import com.tanvoid0.tanquark.models.portfolio.career.Career;
import com.tanvoid0.tanquark.models.portfolio.career.CareerRepository;
import com.tanvoid0.tanquark.models.portfolio.career.organization.academic.Academic;
import com.tanvoid0.tanquark.models.portfolio.career.organization.academic.AcademicRepository;
import com.tanvoid0.tanquark.models.portfolio.career.organization.academic.vo.AcademicMapper;
import com.tanvoid0.tanquark.models.portfolio.career.organization.academic.vo.AcademicVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.academic.vo.NewAcademicVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.Achievement;
import com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.AchievementRepository;
import com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.vo.AchievementMapper;
import com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.vo.AchievementVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.achievement.vo.NewAchievementVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.Certificate;
import com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.CertificateRepository;
import com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.vo.CertificateMapper;
import com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.vo.CertificateVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.certificate.vo.NewCertificateVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.experience.Experience;
import com.tanvoid0.tanquark.models.portfolio.career.organization.experience.ExperienceRepository;
import com.tanvoid0.tanquark.models.portfolio.career.organization.experience.vo.ExperienceMapper;
import com.tanvoid0.tanquark.models.portfolio.career.organization.experience.vo.ExperienceVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.experience.vo.NewExperienceVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.Voluntary;
import com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.VoluntaryRepository;
import com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.vo.NewVoluntaryVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.vo.VoluntaryMapper;
import com.tanvoid0.tanquark.models.portfolio.career.organization.voluntary.vo.VoluntaryVO;
import com.tanvoid0.tanquark.models.portfolio.career.vo.CareerVO;
import com.tanvoid0.tanquark.models.portfolio.migration.NewCareerMigrationVO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@Transactional
@ApplicationScoped
@RequiredArgsConstructor
public class OrganizationService {

    private final AuthService authService;

    private final CareerRepository careerRepository;
    private final AcademicRepository academicRepository;
    private final AchievementRepository achievementRepository;
    private final CertificateRepository certificateRepository;
    private final ExperienceRepository experienceRepository;
    private final VoluntaryRepository voluntaryRepository;

    private final OrganizationValidator validator;
    private final AcademicMapper academicMapper;
    private final AchievementMapper achievementMapper;
    private final CertificateMapper certificateMapper;
    private final ExperienceMapper experienceMapper;
    private final VoluntaryMapper voluntaryMapper;
    private final ModelMapper mapper = new ModelMapper();

    private final PortfolioUserRepository portfolioUserRepository;

    public CareerVO migrate(final NewCareerMigrationVO request, final String username) {
        final PortfolioUser portfolioUser = portfolioUserRepository.findByUserUsername(username).orElseThrow(() -> new ResourceNotFoundException("PortfolioUser", "username", username));
        final Career career = careerRepository.findOrCreateByUser(portfolioUser);
        final CareerVO careerVO = mapper.map(career, CareerVO.class);

        final List<AcademicVO> academics = request.getAcademics().stream().map(item -> createOrUpdateAcademic(item, career)).toList();
        careerVO.setAcademics(academics);

        final List<AchievementVO> achievements = request.getAchievements().stream().map(item -> createOrUpdateAchievement(item, career)).toList();
        careerVO.setAchievements(achievements);

        final List<CertificateVO> certificates = request.getCertificates().stream().map(item -> createOrUpdateCertificate(item, career)).toList();
        careerVO.setCertificates(certificates);


        final List<ExperienceVO> experiences = request.getExperiences().stream().map(item -> createOrUpdateExperience(item, career)).toList();
        careerVO.setExperiences(experiences);

        final List<VoluntaryVO> voluntaries = request.getVoluntaries().stream().map(item -> createOrUpdateVoluntary(item, career)).toList();
        careerVO.setVoluntaries(voluntaries);

        return careerVO;
    }

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
        final Academic entity = academicMapper.toEntity(request, career);
        entity.persist();

        return academicMapper.toVO(entity);
    }

    public List<AchievementVO> createAchievementBatch(final List<NewAchievementVO> request) {
        return request.stream().map(this::createAchievement).toList();
    }

    public AcademicVO createOrUpdateAcademic(final NewAcademicVO request, final Career career) {
        final Optional<Academic> academic = academicRepository.findByCareerIdIdAndTitleAndInstitution(career.getId(), request.getTitle(), request.getInstitution());
        Academic entity;
        if (academic.isPresent()) {
            entity = academic.get();
            mapper.map(request, entity);
        } else {
            entity = academicMapper.toEntity(request, career);
        }
        entity.persist();
        return academicMapper.toVO(entity);
    }

    public AchievementVO createAchievement(final NewAchievementVO request) {
        final Career career = getCareerForCurrentUser();
        validator.validate(request);
        final Achievement entity = achievementMapper.toEntity(request, career);
        entity.setCareer(career);
        entity.persist();

        return achievementMapper.toVO(entity);
    }

    public List<CertificateVO> createCertificateBatch(final List<NewCertificateVO> request) {
        return request.stream().map(this::createCertificate).toList();
    }

    public AchievementVO createOrUpdateAchievement(final NewAchievementVO request, final Career career) {
        final Optional<Achievement> achievement = achievementRepository.findByCareerIdIdAndTitleAndInstitution(career.getId(), request.getTitle(), request.getInstitution());
        Achievement entity;
        if (achievement.isPresent()) {
            entity = achievement.get();
            mapper.map(request, entity);
        } else {
            entity = achievementMapper.toEntity(request, career);
        }
        entity.persist();
        return achievementMapper.toVO(entity);
    }

    public CertificateVO createCertificate(final NewCertificateVO request) {
        final Career career = getCareerForCurrentUser();
        validator.validate(request);
        final Certificate entity = certificateMapper.toEntity(request, career);
        entity.persist();

        return certificateMapper.toVO(entity);
    }

    public CertificateVO createOrUpdateCertificate(final NewCertificateVO request, final Career career) {
        final Optional<Certificate> certificate = certificateRepository.findByCareerIdIdAndTitleAndInstitution(career.getId(), request.getTitle(), request.getInstitution());
        Certificate entity;
        if (certificate.isPresent()) {
            entity = certificate.get();
            mapper.map(request, entity);
        } else {
            entity = certificateMapper.toEntity(request, career);
        }
        entity.persist();
        return certificateMapper.toVO(entity);
    }

    public List<ExperienceVO> createExperienceBatch(final List<NewExperienceVO> request) {
        return request.stream().map(this::createExperience).toList();
    }

    public ExperienceVO createExperience(final NewExperienceVO request) {
        final Career career = getCareerForCurrentUser();
        validator.validate(request);
        final Experience entity = experienceMapper.toEntity(request, career);
        entity.persist();

        return experienceMapper.toVO(entity);
    }

    public ExperienceVO createOrUpdateExperience(final NewExperienceVO request, final Career career) {
        final Optional<Experience> experience = experienceRepository.findByCareerIdIdAndTitleAndInstitution(career.getId(), request.getTitle(), request.getInstitution());
        Experience entity;
        if (experience.isPresent()) {
            entity = experience.get();
            mapper.map(request, entity);
        } else {
            entity = experienceMapper.toEntity(request, career);
        }
        entity.persist();
        return experienceMapper.toVO(entity);
    }

    public List<VoluntaryVO> createVoluntaryBatch(final List<NewVoluntaryVO> request) {
        return request.stream().map(this::createVoluntary).toList();
    }

    public VoluntaryVO createVoluntary(final NewVoluntaryVO request) {
        final Career career = getCareerForCurrentUser();
        validator.validate(request);
        final Voluntary entity = voluntaryMapper.toEntity(request, career);
        entity.persist();

        return voluntaryMapper.toVO(entity);
    }

    public VoluntaryVO createOrUpdateVoluntary(final NewVoluntaryVO request, final Career career) {
        final Optional<Voluntary> voluntary = voluntaryRepository.findByCareerIdIdAndTitleAndInstitution(career.getId(), request.getTitle(), request.getInstitution());
        Voluntary entity;
        if (voluntary.isPresent()) {
            entity = voluntary.get();
            mapper.map(request, entity);
        } else {
            entity = voluntaryMapper.toEntity(request, career);
        }
        entity.persist();
        return voluntaryMapper.toVO(entity);
    }

    private Career getCareerForCurrentUser() {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();
        return careerRepository.findOrCreateByUser(user);
    }
}
