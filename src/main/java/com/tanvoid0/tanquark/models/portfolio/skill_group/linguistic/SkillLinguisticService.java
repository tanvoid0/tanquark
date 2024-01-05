package com.tanvoid0.tanquark.models.portfolio.skill_group.linguistic;

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
public class SkillLinguisticService {

    private final SkillLinguisticMapper skillLinguisticMapper;

    private final SkillGroupRepository skillGroupRepository;
    private final SkillLinguisticRepository skillLinguisticRepository;

    private final AuthService authService;

    private final ModelMapper mapper = new ModelMapper();

    public List<SkillLinguisticVO> addSkill(final List<NewSkillHardItemVO> items) {
        return items.stream().map(this::addSkill).toList();
    }

    public SkillLinguisticVO addSkill(final NewSkillHardItemVO request) {
        final SkillLinguistic entity = skillLinguisticMapper.toEntity(request, getSkillGroup());
        entity.persist();

        return skillLinguisticMapper.toVO(entity);
    }

    public SkillLinguisticVO migrate(final NewSkillHardItemVO request, final SkillGroup skillGroup) {
        final Optional<SkillLinguistic> linguistic = skillLinguisticRepository.findBySkillGroupAndName(skillGroup.getId(), request.getName());

        SkillLinguistic entity;
        if (linguistic.isPresent()) {
            entity = linguistic.get();
            mapper.map(request, entity);
        } else {
            entity = skillLinguisticMapper.toEntity(request, skillGroup);
        }
        entity.persist();
        return skillLinguisticMapper.toVO(entity);
    }

    public List<SkillLinguisticVO> migrate(final List<NewSkillHardItemVO> request, final SkillGroup skillGroup) {
        return request.stream().map(item -> migrate(item, skillGroup)).toList();
    }

    private SkillGroup getSkillGroup() {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();
        return skillGroupRepository.findOrCreateByUser(user);
    }
}
