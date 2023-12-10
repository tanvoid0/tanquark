package com.tanvoid0.tanquark.models.portfolio.portfolio.project_porfolio;

import com.tanvoid0.tanquark.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanquark.config.auth.AuthService;
import com.tanvoid0.tanquark.models.file.FileItemVO;
import com.tanvoid0.tanquark.models.file.FileService;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.portfolio.Portfolio;
import com.tanvoid0.tanquark.models.portfolio.portfolio.PortfolioRepository;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.util.List;
import java.util.Map;

@ApplicationScoped
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProjectPortfolioService {
    private final RoutingContext routingContext;
    private final ProjectPortfolioMapper projectPortfolioMapper;
    private final ProjectPortfolioRepository projectPortfolioRepository;

    private final PortfolioRepository portfolioRepository;

    private final AuthService authService;
    private final FileService fileService;


    public List<ProjectPortfolioVO> findAll() {
        final Portfolio portfolio = getPortfolio();
        return portfolio.getProjects().stream().map(projectPortfolioMapper::toVO).toList();
    }

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

    public ProjectPortfolioVO attachImages(final long projectId, final MultipartFormDataInput request) {
        Map<String, List<InputPart>> uploadForm = request.getFormDataMap();
        final List<InputPart> inputParts = uploadForm.get("images");
        if (inputParts.isEmpty()) {
            log.debug("No images attached");
            return null;
        }

        final ProjectPortfolio projectPortfolio = projectPortfolioRepository.findByIdOptional(projectId).orElseThrow(() -> new ResourceNotFoundException("ProjectPortfolio", "id", projectId));

        final String serverUrl = (routingContext.request().sslSession() == null ? "http://" : "https://") + routingContext.request().authority().host() + ":" + routingContext.request().authority().port();
        for (InputPart inputPart : inputParts) {
            final FileItemVO item = fileService.writeFile(inputPart, serverUrl);
            projectPortfolio.getImages().add(item.getWebUrl());
        }
        projectPortfolio.persist();
        return projectPortfolioMapper.toVO(projectPortfolio);
    }

    private Portfolio getPortfolio() {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();
        return portfolioRepository.findByUser(user);
    }
}
