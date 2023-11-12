package com.tanvoid0.tanquark.models.portfolio.hobby;

import com.tanvoid0.tanquark.common.base.SwapOrderSequence;
import com.tanvoid0.tanquark.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanquark.config.auth.AuthService;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.hobby.vo.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Stream;

@ApplicationScoped
@Transactional
public class HobbyService {
    @Inject
    HobbyRepository hobbyRepository;

    @Inject
    AuthService authService;

    HobbyMapper hobbyMapper = new HobbyMapperImpl();

    private Hobby findEntityById(final long id) {
        return hobbyRepository.findByIdOptional(id).orElseThrow(() -> new ResourceNotFoundException("Hobby", "id", id));
    }

    public HobbyVO findById(final long id) {
        final Hobby hobby = findEntityById(id);
        return hobbyMapper.toVO(hobby);
    }

    public List<HobbyVO> findHobbyByUserId(final long userId) {
        return hobbyRepository.findAllByUserId(userId).stream().map(item -> hobbyMapper.toVO(item)).toList();
    }

    public HobbyVO addHobby(final NewHobbyVO newVO) {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();

        final Hobby hobby = hobbyMapper.toEntity(newVO);
        hobby.setPortfolioUser(user);
        hobby.persist();
        return hobbyMapper.toVO(hobby);
    }

    public List<HobbyVO> addHobby(final List<NewHobbyVO> newVOs) {
        return newVOs.stream().map(this::addHobby).toList();
    }

    public List<HobbyVO> swap(final SwapOrderSequence request) {
        final Hobby entity1 = findEntityById(request.getId1());
        final Hobby entity2 = findEntityById(request.getId2());
        final long seq1 = entity1.getOrderSeq();
        final long seq2 = entity2.getOrderSeq();

        entity1.setOrderSeq(seq2);
        entity2.setOrderSeq(seq1);
        entity1.persist();
        entity2.persist();
        return Stream.of(entity1, entity2).map(item -> hobbyMapper.toVO(item)).toList();
    }

    public HobbyVO update(final UpdateHobbyVO request) {
        final Hobby entity = findEntityById(request.getId());
        hobbyMapper.copy(request, entity);
        entity.persist();
        return hobbyMapper.toVO(entity);
    }

    public void delete(final long id) {
        hobbyRepository.deleteById(id);
    }

}
