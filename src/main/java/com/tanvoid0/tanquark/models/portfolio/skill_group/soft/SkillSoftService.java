package com.tanvoid0.tanquark.models.portfolio.skill_group.soft;

import com.tanvoid0.tanquark.config.auth.AuthService;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.skill_group.SkillGroup;
import com.tanvoid0.tanquark.models.portfolio.skill_group.SkillGroupRepository;
import com.tanvoid0.tanquark.models.portfolio.skill_group.hard.NewSkillHardItemVO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
@RequiredArgsConstructor
public class SkillSoftService {

    private final SkillSoftMapper skillSoftMapper;

    private final SkillGroupRepository skillGroupRepository;
    private final SkillSoftRepository skillSoftRepository;

    private final AuthService authService;
    private final ModelMapper mapper = new ModelMapper();

    public List<SkillSoftVO> addSkillSoft(final List<NewSkillHardItemVO> items) {
        return items.stream().map(this::addSkillSoft).toList();
    }

    public SkillSoftVO addSkillSoft(final NewSkillHardItemVO request) {
        final SkillSoft entity = skillSoftMapper.toEntity(request, getSkillGroup());
        entity.persist();

        return skillSoftMapper.toVO(entity);
    }

    public SkillSoftVO migrate(final NewSkillHardItemVO request, final SkillGroup skillGroup) {
        final Optional<SkillSoft> skillHard = skillSoftRepository.findBySkillGroupAndName(skillGroup.getId(), request.getName());

        SkillSoft entity;
        if (skillHard.isPresent()) {
            entity = skillHard.get();
            mapper.map(request, entity);
        } else {
            entity = skillSoftMapper.toEntity(request, skillGroup);
        }
        entity.persist();

        return skillSoftMapper.toVO(entity);
    }

    public List<SkillSoftVO> migrate(List<NewSkillHardItemVO> request, SkillGroup skillGroup) {
        return request.stream().map(item -> migrate(item, skillGroup)).toList();
    }

    private SkillGroup getSkillGroup() {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();
        return skillGroupRepository.findOrCreateByUser(user);
    }
}
