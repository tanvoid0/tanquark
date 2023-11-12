package com.tanvoid0.tanquark.models.portfolio.skill_group.language;

import com.tanvoid0.tanquark.config.auth.AuthService;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.skill_group.SkillGroup;
import com.tanvoid0.tanquark.models.portfolio.skill_group.SkillGroupRepository;
import com.tanvoid0.tanquark.models.portfolio.skill_group.hard.NewSkillHardItemVO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@Transactional
@RequiredArgsConstructor
public class SkillLanguageService {

    private final SkillLanguageMapper skillLanguageMapper;

    private final SkillGroupRepository skillGroupRepository;

    private final AuthService authService;

    public SkillLanguageVO addSkill(final NewSkillHardItemVO request) {
        final SkillLanguage entity = skillLanguageMapper.toEntity(request);
        entity.setSkillGroup(getSkillGroup());
        entity.persist();
        return skillLanguageMapper.toVO(entity);
    }

    public List<SkillLanguageVO> addSkill(final List<NewSkillHardItemVO> request) {
        return request.stream().map(this::addSkill).toList();
    }

    private SkillGroup getSkillGroup() {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();
        return skillGroupRepository.findByUser(user);
    }
}
