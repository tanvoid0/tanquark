package com.tanvoid0.tanquark.models.portfolio.portfolio.project;


import com.tanvoid0.tanquark.models.portfolio.portfolio.Portfolio;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class ProjectPortfolioMapper {
    private final ModelMapper mapper = new ModelMapper();

    public Project toEntity(final NewProjectPortfolioVO newVO, final Portfolio portfolio) {
        final Project entity = mapper.map(newVO, Project.class);
        entity.setPortfolio(portfolio);
        return entity;
    }

    public ProjectPortfolioVO toVO(final Project entity) {
        return mapper.map(entity, ProjectPortfolioVO.class);
    }

}
