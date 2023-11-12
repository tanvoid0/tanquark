package com.tanvoid0.tanquark.models.portfolio.skill_group.hard;

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
public class SkillHardService {

    private final SkillHardMapper skillHardMapper;

    private final SkillGroupRepository skillGroupRepository;

    private final AuthService authService;

    public SkillHardVO add(final NewSkillHardVO newVO) {
        final SkillHard entity = skillHardMapper.toEntity(newVO);
        entity.setSkillGroup(getSkillGroup());
        entity.persist();

        final SkillHardVO vo = skillHardMapper.toVO(entity);
        vo.setItems(this.add(newVO.getItems(), entity));

        return vo;
    }

    public SkillHardItemVO add(final NewSkillHardItemVO request, final SkillHard parentEntity) {
        final SkillHardItem entity = skillHardMapper.toEntity(request, parentEntity);
        entity.persist();
        return skillHardMapper.toVO(entity);
    }

    public List<SkillHardItemVO> add(final List<NewSkillHardItemVO> request, final SkillHard parentEntity) {
        return request.stream().map(item -> this.add(item, parentEntity)).toList();
    }

    public List<SkillHardVO> add(final List<NewSkillHardVO> request) {
        return request.stream().map(this::add).toList();
    }

    private SkillGroup getSkillGroup() {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();
        return skillGroupRepository.findByUser(user);
    }
}
