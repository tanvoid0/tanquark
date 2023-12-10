package com.tanvoid0.tanquark.models.portfolio.portfolio_contact_request;

import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class PortfolioContactRequestMapper {
    private final ModelMapper mapper = new ModelMapper();

    public PortfolioContactRequest toEntity(final NewPortfolioContactRequestVO newVO) {
        return mapper.map(newVO, PortfolioContactRequest.class);
    }

    public PortfolioContactRequestVO toVO(final PortfolioContactRequest entity) {
        return mapper.map(entity, PortfolioContactRequestVO.class);
    }
}
