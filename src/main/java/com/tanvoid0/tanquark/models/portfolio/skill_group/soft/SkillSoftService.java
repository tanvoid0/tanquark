package com.tanvoid0.tanquark.models.portfolio.skill_group.soft;

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
public class SkillSoftService {

    private final SkillSoftMapper skillSoftMapper;

    private final SkillGroupRepository skillGroupRepository;

    private final AuthService authService;

    public List<SkillSoftVO> addSkillSoft(final List<NewSkillHardItemVO> items) {
        return items.stream().map(this::addSkillSoft).toList();
    }

    public SkillSoftVO addSkillSoft(final NewSkillHardItemVO request) {
        final SkillSoft entity = skillSoftMapper.toEntity(request);
        entity.setSkillGroup(getSkillGroup());
        entity.persist();

        return skillSoftMapper.toVO(entity);
    }

    private SkillGroup getSkillGroup() {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();
        return skillGroupRepository.findByUser(user);
    }
}
