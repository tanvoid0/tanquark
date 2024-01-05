package com.tanvoid0.tanquark.models.portfolio.portfolio.online_judge;

import com.tanvoid0.tanquark.config.auth.AuthService;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.portfolio.Portfolio;
import com.tanvoid0.tanquark.models.portfolio.portfolio.PortfolioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
@RequiredArgsConstructor
public class OnlineJudgeService {

    private final OnlineJudgePortfolioMapper onlineJudgePortfolioMapper;

    private final PortfolioRepository portfolioRepository;
    private final OnlineJudgeRepository onlineJudgeRepository;

    private final AuthService authService;
    private final ModelMapper mapper = new ModelMapper();

    public OnlineJudgeVO add(final NewOnlineJudgeVO request) {
        final OnlineJudge entity = onlineJudgePortfolioMapper.toEntity(request, getPortfolio());
        entity.persist();
        return onlineJudgePortfolioMapper.toVO(entity);
    }

    public List<OnlineJudgeVO> add(final List<NewOnlineJudgeVO> request) {
        return request.stream().map(this::add).toList();
    }

    private Portfolio getPortfolio() {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();
        return portfolioRepository.findOrCreateByUser(user);
    }

    public OnlineJudgeVO migrate(final NewOnlineJudgeVO request, final Portfolio portfolio) {
        final Optional<OnlineJudge> onlineJudge = onlineJudgeRepository.findByPortfolioAndName(portfolio.getId(), request.getName());
        OnlineJudge entity;
        if (onlineJudge.isPresent()) {
            entity = onlineJudge.get();
            mapper.map(request, entity);
        } else {
            entity = onlineJudgePortfolioMapper.toEntity(request, portfolio);
        }
        entity.persist();
        return onlineJudgePortfolioMapper.toVO(entity);
    }

    public List<OnlineJudgeVO> migrate(final List<NewOnlineJudgeVO> request, final Portfolio portfolio) {
        return request.stream().map(item -> this.migrate(item, portfolio)).toList();
    }

    // TODO: make a configurable service that would fetch the online judge portfolio by caching the data periodically


}
