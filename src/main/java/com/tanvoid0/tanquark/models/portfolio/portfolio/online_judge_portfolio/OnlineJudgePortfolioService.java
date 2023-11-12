package com.tanvoid0.tanquark.models.portfolio.portfolio.online_judge_portfolio;

import com.tanvoid0.tanquark.config.auth.AuthService;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.portfolio.Portfolio;
import com.tanvoid0.tanquark.models.portfolio.portfolio.PortfolioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@Transactional
@RequiredArgsConstructor
public class OnlineJudgePortfolioService {

    private final OnlineJudgePortfolioMapper onlineJudgePortfolioMapper;

    private final PortfolioRepository portfolioRepository;

    private final AuthService authService;

    public OnlineJudgePortfolioVO add(final NewOnlineJudgePortfolioVO request) {
        final OnlineJudgePortfolio entity = onlineJudgePortfolioMapper.toEntity(request);
        entity.setPortfolio(getPortfolio());
        entity.persist();
        return onlineJudgePortfolioMapper.toVO(entity);
    }

    public List<OnlineJudgePortfolioVO> add(final List<NewOnlineJudgePortfolioVO> request) {
        return request.stream().map(this::add).toList();
    }

    private Portfolio getPortfolio() {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();
        return portfolioRepository.findByUser(user);
    }

    // TODO: make a configurable service that would fetch the online judge portfolio by caching the data periodically


}
