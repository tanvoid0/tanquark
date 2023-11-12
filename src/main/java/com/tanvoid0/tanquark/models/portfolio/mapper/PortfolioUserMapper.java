package com.tanvoid0.tanquark.models.portfolio.mapper;

import com.tanvoid0.tanquark.models.portfolio.NewPortfolioUserVO;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUserVO;
import org.mapstruct.factory.Mappers;

public interface PortfolioUserMapper {
    PortfolioUserMapper INSTANCE = Mappers.getMapper(PortfolioUserMapper.class);

    public PortfolioUser toEntity(NewPortfolioUserVO newVO);

    public PortfolioUserVO toVO(PortfolioUser entity);
}
