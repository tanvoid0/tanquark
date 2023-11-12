package com.tanvoid0.tanquark.models.portfolio.portfolio.project_porfolio;

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
public class ProjectPortfolioService {
    private final ProjectPortfolioMapper projectPortfolioMapper;

    private final PortfolioRepository portfolioRepository;

    private final AuthService authService;

    public ProjectPortfolioVO add(final NewProjectPortfolioVO request) {
        final ProjectPortfolio entity = projectPortfolioMapper.toEntity(request);
        entity.setPortfolio(getPortfolio());
        entity.persist();
        ;
        return projectPortfolioMapper.toVO(entity);
    }

    public List<ProjectPortfolioVO> add(final List<NewProjectPortfolioVO> request) {
        return request.stream().map(this::add).toList();
    }

    private Portfolio getPortfolio() {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();
        return portfolioRepository.findByUser(user);
    }
}
