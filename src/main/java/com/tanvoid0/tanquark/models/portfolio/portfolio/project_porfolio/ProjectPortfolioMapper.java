package com.tanvoid0.tanquark.models.portfolio.portfolio.project_porfolio;


import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class ProjectPortfolioMapper {
    private final ModelMapper mapper = new ModelMapper();

    public ProjectPortfolio toEntity(final NewProjectPortfolioVO newVO) {
        return mapper.map(newVO, ProjectPortfolio.class);
    }

    public ProjectPortfolioVO toVO(final ProjectPortfolio entity) {
        return mapper.map(entity, ProjectPortfolioVO.class);
    }

}
