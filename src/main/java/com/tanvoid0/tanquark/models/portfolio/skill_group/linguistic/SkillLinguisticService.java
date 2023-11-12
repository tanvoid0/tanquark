package com.tanvoid0.tanquark.models.portfolio.skill_group.linguistic;

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
public class SkillLinguisticService {

    private final SkillLinguisticMapper skillLinguisticMapper;

    private final SkillGroupRepository skillGroupRepository;

    private final AuthService authService;

    public List<SkillLinguisticVO> addSkill(final List<NewSkillHardItemVO> items) {
        return items.stream().map(this::addSkill).toList();
    }

    public SkillLinguisticVO addSkill(final NewSkillHardItemVO request) {
        final SkillLinguistic entity = skillLinguisticMapper.toEntity(request);
        entity.setSkillGroup(getSkillGroup());
        entity.persist();

        return skillLinguisticMapper.toVO(entity);
    }

    private SkillGroup getSkillGroup() {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();
        return skillGroupRepository.findByUser(user);
    }
}
