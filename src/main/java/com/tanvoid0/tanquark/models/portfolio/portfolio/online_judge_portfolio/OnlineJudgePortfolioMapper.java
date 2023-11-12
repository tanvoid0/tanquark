package com.tanvoid0.tanquark.models.portfolio.portfolio.online_judge_portfolio;

import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class OnlineJudgePortfolioMapper {

    private final ModelMapper mapper = new ModelMapper();

    public OnlineJudgePortfolio toEntity(final NewOnlineJudgePortfolioVO newVO) {
        return mapper.map(newVO, OnlineJudgePortfolio.class);
    }

    public OnlineJudgePortfolioVO toVO(final OnlineJudgePortfolio entity) {
        return mapper.map(entity, OnlineJudgePortfolioVO.class);
    }
}
