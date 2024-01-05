package com.tanvoid0.tanquark.models.portfolio.migration;

import com.tanvoid0.tanquark.common.exception.InvalidRequestException;
import com.tanvoid0.tanquark.common.exception.ValidationProperty;
import com.tanvoid0.tanquark.config.auth.AuthService;
import com.tanvoid0.tanquark.config.auth.vo.AuthenticatedUserVO;
import com.tanvoid0.tanquark.config.auth.vo.RegisterRequestVO;
import com.tanvoid0.tanquark.models.portfolio.NewPortfolioUserVO;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUserService;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUserVO;
import com.tanvoid0.tanquark.models.portfolio.career.organization.OrganizationService;
import com.tanvoid0.tanquark.models.portfolio.career.vo.CareerVO;
import com.tanvoid0.tanquark.models.portfolio.hobby.HobbyService;
import com.tanvoid0.tanquark.models.portfolio.hobby.vo.HobbyVO;
import com.tanvoid0.tanquark.models.portfolio.portfolio.PortfolioService;
import com.tanvoid0.tanquark.models.portfolio.portfolio.PortfolioVO;
import com.tanvoid0.tanquark.models.portfolio.skill_group.SkillGroupService;
import com.tanvoid0.tanquark.models.portfolio.skill_group.SkillGroupVO;
import com.tanvoid0.tanquark.models.portfolio.social.SocialService;
import com.tanvoid0.tanquark.models.portfolio.social.vo.SocialVO;
import com.tanvoid0.tanquark.models.user.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MigrationService {
    private final MigrationMapper mapper;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final PortfolioUserService portfolioUserService;
    private final PortfolioService portfolioService;
    private final SkillGroupService skillGroupService;
    private final HobbyService hobbyService;
    private final SocialService socialService;
    private final OrganizationService organizationService;

    public PortfolioUserVO migrate(final NewPortfolioUserMigrationVO request) {
        // TODO: Don't allow unauthenticated users in future. Make sure user registers first, so that user doesn't override someone else's data.

        // Register User
//        AuthenticatedUserVO user = authService.migrate(request);
//        PortfolioUserVO portfolioUserVO = portfolioUserService.migrate(request, user.getUsername());
        AuthenticatedUserVO user;
        PortfolioUserVO portfolioUserVO;
        try {
            final RegisterRequestVO registerRequestVO = mapper.mapper.map(request, RegisterRequestVO.class);
            user = authService.register(registerRequestVO);
            log.info("User: {}", user.getUsername());
        } catch (final InvalidRequestException ex) {
            if (ex.getValidations().isEmpty()) {
                throw ex;
            }
//            item.getProperty().equals("email") && Pattern.compile("^User with email .+ already exists$").matcher(item.getValue().find())
            final Optional<ValidationProperty> validation = ex.getValidations().stream().filter(item -> item.getProperty().equals("email") && item.getValue().equals(String.format("User with email %s already exists", request.getEmail()))).findFirst();

            if (validation.isEmpty()) {
                throw ex;
            }
            user = authService.authenticate(request.getEmail());

            // TODO: additional required validation handler
        }

        //         Create Portfolio
        try {
            final NewPortfolioUserVO newPortfolioUserVO = mapper.mapper.map(request, NewPortfolioUserVO.class);
            portfolioUserVO = portfolioUserService.create(newPortfolioUserVO, user.getUsername());
        } catch (final InvalidRequestException ex) {
            if (ex.getValidations().isEmpty()) {
                throw ex;
            }
            final Optional<ValidationProperty> validation = ex.getValidations().stream().filter(item -> item.getProperty().equals("username") && item.getValue().equals(String.format("Portfolio User already exists with username %s", request.getUsername()))).findFirst();
            if (validation.isEmpty()) {
                throw ex;
            }
            portfolioUserVO = portfolioUserService.findByUsernameFull(request.getUsername());
            // TODO: additional required validation handler
        }
//
        // Hobbies
        final List<HobbyVO> hobbies = hobbyService.migrate(request.getHobbies(), portfolioUserVO.getUsername());
        portfolioUserVO.setHobbies(hobbies);

        // Socials
        final List<SocialVO> socials = socialService.migrate(request.getSocials(), portfolioUserVO.getUsername());
        portfolioUserVO.setSocials(socials);

        // Career
        final CareerVO career = organizationService.migrate(request.getCareer(), portfolioUserVO.getUsername());
        portfolioUserVO.setCareer(career);

        final PortfolioVO portfolioVO = portfolioService.migrate(request.getPortfolio(), portfolioUserVO.getUsername());
        portfolioUserVO.setPortfolio(portfolioVO);

        final SkillGroupVO skillVO = skillGroupService.migrate(request.getSkill(), portfolioUserVO.getUsername());
        portfolioUserVO.setSkill(skillVO);

        // update user at the end of all updates

        return portfolioUserVO;
    }
}
