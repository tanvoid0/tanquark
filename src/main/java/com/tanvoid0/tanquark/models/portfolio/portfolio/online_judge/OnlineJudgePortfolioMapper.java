package com.tanvoid0.tanquark.models.portfolio.portfolio.online_judge;

import com.tanvoid0.tanquark.models.portfolio.portfolio.Portfolio;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class OnlineJudgePortfolioMapper {

    private final ModelMapper mapper = new ModelMapper();

    public OnlineJudge toEntity(final NewOnlineJudgeVO newVO, final Portfolio portfolio) {
        final OnlineJudge entity = mapper.map(newVO, OnlineJudge.class);
        entity.setPortfolio(portfolio);
        return entity;
    }

    public OnlineJudgeVO toVO(final OnlineJudge entity) {
        return mapper.map(entity, OnlineJudgeVO.class);
    }
}
