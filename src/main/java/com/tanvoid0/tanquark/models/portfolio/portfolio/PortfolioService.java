package com.tanvoid0.tanquark.models.portfolio.portfolio;

import com.tanvoid0.tanquark.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUserRepository;
import com.tanvoid0.tanquark.models.portfolio.migration.NewPortfolioMigrationVO;
import com.tanvoid0.tanquark.models.portfolio.portfolio.online_judge.OnlineJudgeService;
import com.tanvoid0.tanquark.models.portfolio.portfolio.online_judge.OnlineJudgeVO;
import com.tanvoid0.tanquark.models.portfolio.portfolio.project.ProjectPortfolioService;
import com.tanvoid0.tanquark.models.portfolio.portfolio.project.ProjectPortfolioVO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@Transactional
@ApplicationScoped
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioUserRepository portfolioUserRepository;
    private final PortfolioRepository portfolioRepository;
    private final OnlineJudgeService onlineJudgeService;
    private final ProjectPortfolioService projectPortfolioService;

    private final ModelMapper mapper = new ModelMapper();

    public PortfolioVO migrate(final NewPortfolioMigrationVO request, final String username) {
        final PortfolioUser portfolioUser = portfolioUserRepository.findByUserUsername(username).orElseThrow(() -> new ResourceNotFoundException("PortfolioUser", "username", username));
        final Portfolio portfolio = portfolioRepository.findOrCreateByUser(portfolioUser);

        final PortfolioVO portfolioVO = mapper.map(portfolio, PortfolioVO.class);

        final List<OnlineJudgeVO> onlineJudges = onlineJudgeService.migrate(request.getOnlineJudges(), portfolio);
        portfolioVO.setOnlineJudges(onlineJudges);

        final List<ProjectPortfolioVO> projects = projectPortfolioService.migrate(request.getProjects(), portfolio);
        portfolioVO.setProjects(projects);

        return portfolioVO;
    }
}
