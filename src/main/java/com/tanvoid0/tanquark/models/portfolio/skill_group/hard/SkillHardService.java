package com.tanvoid0.tanquark.models.portfolio.skill_group.hard;

import com.tanvoid0.tanquark.config.auth.AuthService;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.skill_group.SkillGroup;
import com.tanvoid0.tanquark.models.portfolio.skill_group.SkillGroupRepository;
import com.tanvoid0.tanquark.models.portfolio.skill_group.framework.SkillHardRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
@RequiredArgsConstructor
public class SkillHardService {

    private final SkillHardMapper skillHardMapper;

    private final SkillGroupRepository skillGroupRepository;
    private final SkillHardRepository skillHardRepository;
    private final SkillHardItemRepository skillHardItemRepository;

    private final AuthService authService;
    private final ModelMapper mapper = new ModelMapper();

    public SkillHardVO add(final NewSkillHardVO newVO) {
        final SkillHard entity = skillHardMapper.toEntity(newVO, getSkillGroup());
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
        return skillGroupRepository.findOrCreateByUser(user);
    }

    public SkillHardItemVO migrate(final NewSkillHardItemVO request, final SkillHard skillHard) {
        final Optional<SkillHardItem> skillHardItem = skillHardItemRepository.findBySkillHardAndName(skillHard.getId(), request.getName());

        SkillHardItem entity;
        if (skillHardItem.isPresent()) {
            entity = skillHardItem.get();
            mapper.map(request, entity);
        } else {
            entity = skillHardMapper.toEntity(request, skillHard);
        }
        entity.persist();
        return skillHardMapper.toVO(entity);
    }

    public SkillHardVO migrate(final NewSkillHardVO request, final SkillGroup skillGroup) {
        final Optional<SkillHard> skillHard = skillHardRepository.findBySkillGroupAndName(skillGroup.getId(), request.getName());

        SkillHard entity;
        if (skillHard.isPresent()) {
            entity = skillHard.get();
            final UpdateSkillHardVO updateVO = mapper.map(request, UpdateSkillHardVO.class);
            mapper.map(updateVO, entity);
        } else {
            entity = skillHardMapper.toEntity(request, skillGroup);
        }
        entity.persistAndFlush();

        final SkillHardVO vo = skillHardMapper.toVO(entity);
        final List<SkillHardItemVO> items = request.getItems().stream().map(item -> this.migrate(item, entity)).toList();
        vo.setItems(items);
        return vo;
    }

    public List<SkillHardVO> migrate(List<NewSkillHardVO> request, SkillGroup skillGroup) {
        return request.stream().map(item -> migrate(item, skillGroup)).toList();
    }
}
