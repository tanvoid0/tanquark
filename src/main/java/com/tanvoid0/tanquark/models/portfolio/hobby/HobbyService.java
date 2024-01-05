package com.tanvoid0.tanquark.models.portfolio.hobby;

import com.tanvoid0.tanquark.common.base.SwapOrderSequence;
import com.tanvoid0.tanquark.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanquark.config.auth.AuthService;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUserRepository;
import com.tanvoid0.tanquark.models.portfolio.hobby.vo.HobbyMapper;
import com.tanvoid0.tanquark.models.portfolio.hobby.vo.HobbyVO;
import com.tanvoid0.tanquark.models.portfolio.hobby.vo.NewHobbyVO;
import com.tanvoid0.tanquark.models.portfolio.hobby.vo.UpdateHobbyVO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
@Transactional
@RequiredArgsConstructor
public class HobbyService {
    private final HobbyRepository hobbyRepository;
    private final PortfolioUserRepository portfolioUserRepository;

    private final AuthService authService;

    private final HobbyMapper hobbyMapper;

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

    public HobbyVO addHobby(final NewHobbyVO newVO, final PortfolioUser user) {
        final Hobby hobby = hobbyMapper.toEntity(newVO, user);
        hobby.persist();
        return hobbyMapper.toVO(hobby);
    }

    public HobbyVO addHobby(final NewHobbyVO newVO) {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();
        return addHobby(newVO, user);
    }

    public List<HobbyVO> addHobby(final List<NewHobbyVO> newVOs) {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();

        return newVOs.stream().map(item -> addHobby(item, user)).toList();
    }

    public HobbyVO migrate(final NewHobbyVO request, final PortfolioUser user) {
        final Optional<Hobby> hobby = hobbyRepository.findByUserIdAndTitle(user.getId(), request.getTitle());

        Hobby entity;

        if (hobby.isPresent()) {
            entity = hobby.get();
            hobbyMapper.mapper.map(request, entity);
        } else {
            entity = hobbyMapper.toEntity(request, user);
        }
        entity.persistAndFlush();
        return hobbyMapper.toVO(entity);
    }

    public List<HobbyVO> migrate(final List<NewHobbyVO> request, final String username) {
        final PortfolioUser portfolioUser = portfolioUserRepository.findByUserUsername(username).orElseThrow(() -> new ResourceNotFoundException("PortfolioUser", "username", username));

        return request.stream().map(item -> this.migrate(item, portfolioUser)).toList();
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
