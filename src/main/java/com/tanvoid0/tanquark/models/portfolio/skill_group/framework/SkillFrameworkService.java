package com.tanvoid0.tanquark.models.portfolio.skill_group.framework;

import com.tanvoid0.tanquark.config.auth.AuthService;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.skill_group.SkillGroup;
import com.tanvoid0.tanquark.models.portfolio.skill_group.SkillGroupRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
@RequiredArgsConstructor
public class SkillFrameworkService {

    private final SkillFrameworkMapper skillFrameworkMapper;

    private final SkillGroupRepository skillGroupRepository;
    private final SkillFrameworkRepository skillFrameworkRepository;
    private final AuthService authService;
    private ModelMapper mapper = new ModelMapper();

    public List<SkillFrameworkVO> add(final List<NewSkillFrameworkVO> request) {
        return request.stream().map(this::add).toList();
    }

    public SkillFrameworkVO add(final NewSkillFrameworkVO request) {
        final SkillFramework entity = skillFrameworkMapper.toEntity(request, getSkillGroup());
        entity.persist();
        return skillFrameworkMapper.toVO(entity);
    }

    public SkillGroup getSkillGroup() {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();

        return skillGroupRepository.findOrCreateByUser(user);
    }

    public SkillFrameworkVO migrate(final NewSkillFrameworkVO request, final SkillGroup skillGroup) {
        final Optional<SkillFramework> framework = skillFrameworkRepository.findBySkillGroupAndName(skillGroup.getId(), request.getName());

        SkillFramework entity;
        if (framework.isPresent()) {
            entity = framework.get();
            mapper.map(request, entity);
        } else {
            entity = skillFrameworkMapper.toEntity(request, skillGroup);
        }
        entity.persist();
        return skillFrameworkMapper.toVO(entity);
    }

    public List<SkillFrameworkVO> migrate(List<NewSkillFrameworkVO> frameworks, SkillGroup skillGroup) {
        return frameworks.stream().map(item -> migrate(item, skillGroup)).toList();
    }
}
