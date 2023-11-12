package com.tanvoid0.tanquark.models.portfolio.social;

import com.tanvoid0.tanquark.common.base.SwapOrderSequence;
import com.tanvoid0.tanquark.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanquark.config.auth.AuthService;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.social.vo.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Stream;

@ApplicationScoped
@Transactional
public class SocialService {
    @Inject
    SocialRepository socialRepository;

    @Inject
    AuthService authService;

    SocialMapper socialMapper = new SocialMapperImpl();

    private Social findEntityById(final long id) {
        return socialRepository.findByIdOptional(id).orElseThrow(() -> new ResourceNotFoundException("Hobby", "id", id));
    }

    public SocialVO findById(final long id) {
        final Social social = findEntityById(id);
        return socialMapper.toVO(social);
    }

    public List<SocialVO> findHobbyByUserId(final long userId) {
        return socialRepository.findAllByUserId(userId).stream().map(item -> socialMapper.toVO(item)).toList();
    }

    public SocialVO addHobby(final NewSocialVO newVO) {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();

        final Social social = socialMapper.toEntity(newVO);
        social.setPortfolioUser(user);
        social.persist();
        return socialMapper.toVO(social);
    }

    public List<SocialVO> addHobby(final List<NewSocialVO> newVOs) {
        return newVOs.stream().map(this::addHobby).toList();
    }

    public List<SocialVO> swap(final SwapOrderSequence request) {
        final Social entity1 = findEntityById(request.getId1());
        final Social entity2 = findEntityById(request.getId2());
        final long seq1 = entity1.getOrderSeq();
        final long seq2 = entity2.getOrderSeq();

        entity1.setOrderSeq(seq2);
        entity2.setOrderSeq(seq1);
        entity1.persist();
        entity2.persist();
        return Stream.of(entity1, entity2).map(item -> socialMapper.toVO(item)).toList();
    }

    public SocialVO update(final UpdateSocialVO request) {
        final Social entity = findEntityById(request.getId());
        socialMapper.copy(request, entity);
        entity.persist();
        return socialMapper.toVO(entity);
    }

    public void delete(final long id) {
        socialRepository.deleteById(id);
    }

}
