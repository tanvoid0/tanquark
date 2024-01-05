package com.tanvoid0.tanquark.models.portfolio.skill_group;

import com.tanvoid0.tanquark.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUserRepository;
import com.tanvoid0.tanquark.models.portfolio.migration.NewSkillMigrationVO;
import com.tanvoid0.tanquark.models.portfolio.skill_group.framework.SkillFrameworkService;
import com.tanvoid0.tanquark.models.portfolio.skill_group.framework.SkillFrameworkVO;
import com.tanvoid0.tanquark.models.portfolio.skill_group.hard.SkillHardService;
import com.tanvoid0.tanquark.models.portfolio.skill_group.hard.SkillHardVO;
import com.tanvoid0.tanquark.models.portfolio.skill_group.language.SkillLanguageService;
import com.tanvoid0.tanquark.models.portfolio.skill_group.language.SkillLanguageVO;
import com.tanvoid0.tanquark.models.portfolio.skill_group.linguistic.SkillLinguisticService;
import com.tanvoid0.tanquark.models.portfolio.skill_group.linguistic.SkillLinguisticVO;
import com.tanvoid0.tanquark.models.portfolio.skill_group.soft.SkillSoftService;
import com.tanvoid0.tanquark.models.portfolio.skill_group.soft.SkillSoftVO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@ApplicationScoped
@Transactional
@RequiredArgsConstructor
public class SkillGroupService {
    private final SkillGroupRepository skillGroupRepository;
    private final PortfolioUserRepository portfolioUserRepository;
    private final SkillLinguisticService skillLinguisticService;
    private final SkillLanguageService skillLanguageService;
    private final SkillFrameworkService skillFrameworkService;
    private final SkillHardService skillHardService;
    private final SkillSoftService skillSoftService;
    private final ModelMapper mapper = new ModelMapper();

    public SkillGroupVO migrate(final NewSkillMigrationVO request, final String username) {
        final PortfolioUser portfolioUser = portfolioUserRepository.findByUserUsername(username).orElseThrow(() -> new ResourceNotFoundException("PortfolioUser", "username", username));

        final SkillGroup skillGroup = skillGroupRepository.findOrCreateByUser(portfolioUser);
        final SkillGroupVO skillGroupVO = mapper.map(skillGroup, SkillGroupVO.class);

        // soft skills
        final List<SkillSoftVO> softSkills = skillSoftService.migrate(request.getSoftSkills(), skillGroup);
        skillGroupVO.setSoftSkills(softSkills);

        // hard skills
        final List<SkillHardVO> hardSkills = skillHardService.migrate(request.getHardSkills(), skillGroup);
        skillGroupVO.setHardSkills(hardSkills);

        // linguistics
        final List<SkillLinguisticVO> linguistics = skillLinguisticService.migrate(request.getLinguisticSkills(), skillGroup);
        skillGroupVO.setLinguisticSkills(linguistics);

        // languages
        final List<SkillLanguageVO> languages = skillLanguageService.migrate(request.getLanguages(), skillGroup);
        skillGroupVO.setLanguages(languages);

        // frameworks
        final List<SkillFrameworkVO> frameworks = skillFrameworkService.migrate(request.getFrameworks(), skillGroup);
        skillGroupVO.setFrameworks(frameworks);

        // libraries


        // soft skills
        return skillGroupVO;
    }

}
