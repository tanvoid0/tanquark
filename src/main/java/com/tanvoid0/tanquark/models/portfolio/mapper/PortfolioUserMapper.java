package com.tanvoid0.tanquark.models.portfolio.mapper;

import com.tanvoid0.tanquark.models.portfolio.NewPortfolioUserVO;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUserVO;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class PortfolioUserMapper {
    private ModelMapper mapper = new ModelMapper();

    public PortfolioUser toEntity(NewPortfolioUserVO newVO) {
        return mapper.map(newVO, PortfolioUser.class);
    }

    public PortfolioUserVO toVO(PortfolioUser entity) {
        final PortfolioUserVO vo = mapper.map(entity, PortfolioUserVO.class);
        vo.setName(entity.getUser().getName());
        vo.setEmail(entity.getUser().getEmail());
        vo.setUsername(entity.getUser().getUsername());
        vo.setPhone(entity.getUser().getPhone());
        return vo;
    }
}
