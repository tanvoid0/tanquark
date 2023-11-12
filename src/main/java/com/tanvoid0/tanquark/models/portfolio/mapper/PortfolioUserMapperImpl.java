package com.tanvoid0.tanquark.models.portfolio.mapper;

import com.tanvoid0.tanquark.models.portfolio.NewPortfolioUserVO;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUserVO;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class PortfolioUserMapperImpl implements PortfolioUserMapper {
    ModelMapper mapper = new ModelMapper();

    @Override
    public PortfolioUser toEntity(NewPortfolioUserVO newVO) {
        return mapper.map(newVO, PortfolioUser.class);
    }

    @Override
    public PortfolioUserVO toVO(PortfolioUser entity) {
        return mapper.map(entity, PortfolioUserVO.class);
    }
}
