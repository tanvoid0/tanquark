package com.tanvoid0.tanquark.models.portfolio.skill_group.framework;

import com.tanvoid0.tanquark.config.auth.AuthService;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.skill_group.SkillGroup;
import com.tanvoid0.tanquark.models.portfolio.skill_group.SkillGroupRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@Transactional
@RequiredArgsConstructor
public class SkillFrameworkService {

    private final SkillFrameworkMapper skillFrameworkMapper;

    private final SkillGroupRepository skillGroupRepository;
    private final AuthService authService;

    public List<SkillFrameworkVO> add(final List<NewSkillFrameworkVO> request) {
        return request.stream().map(this::add).toList();
    }

    public SkillFrameworkVO add(final NewSkillFrameworkVO request) {
        final SkillFramework entity = skillFrameworkMapper.toEntity(request);
        entity.setSkillGroup(getSkillGroup());
        entity.persist();
        return skillFrameworkMapper.toVO(entity);
    }

    public SkillGroup getSkillGroup() {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();

        return skillGroupRepository.findByUser(user);
    }

}
