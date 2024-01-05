package com.tanvoid0.tanquark.models.portfolio.skill_group.language;

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
public class SkillLanguageService {

    private final SkillLanguageMapper skillLanguageMapper;

    private final SkillGroupRepository skillGroupRepository;
    private final SkillLanguageRepository skillLanguageRepository;

    private final AuthService authService;

    private final ModelMapper mapper = new ModelMapper();

    public SkillLanguageVO addSkill(final NewSkillHardItemVO request) {
        final SkillLanguage entity = skillLanguageMapper.toEntity(request, getSkillGroup());
        entity.persist();
        return skillLanguageMapper.toVO(entity);
    }

    public List<SkillLanguageVO> addSkill(final List<NewSkillHardItemVO> request) {
        return request.stream().map(this::addSkill).toList();
    }

    public SkillLanguageVO migrate(final NewSkillHardItemVO request, final SkillGroup skillGroup) {
        final Optional<SkillLanguage> language = skillLanguageRepository.findBySkillGroupAndName(skillGroup.getId(), request.getName());

        SkillLanguage entity;
        if (language.isPresent()) {
            entity = language.get();
            mapper.map(request, entity);
        } else {
            entity = skillLanguageMapper.toEntity(request, skillGroup);
        }
        entity.persist();
        return skillLanguageMapper.toVO(entity);
    }


    public List<SkillLanguageVO> migrate(final List<NewSkillHardItemVO> request, final SkillGroup skillGroup) {
        return request.stream().map(item -> migrate(item, skillGroup)).toList();
    }

    private SkillGroup getSkillGroup() {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();
        return skillGroupRepository.findOrCreateByUser(user);
    }
}
