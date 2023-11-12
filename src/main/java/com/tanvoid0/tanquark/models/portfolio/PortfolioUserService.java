package com.tanvoid0.tanquark.models.portfolio;

import com.tanvoid0.tanquark.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanquark.models.portfolio.mapper.PortfolioUserMapper;
import com.tanvoid0.tanquark.models.user.User;
import com.tanvoid0.tanquark.models.user.UserRepository;
import com.tanvoid0.tanquark.models.user.role.ERole;
import com.tanvoid0.tanquark.models.user.role.Role;
import com.tanvoid0.tanquark.models.user.role.RoleRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class PortfolioUserService {

    @Inject
    PortfolioUserRepository portfolioUserRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    RoleRepository roleRepository;

    @Inject
    PortfolioUserValidator validator;


    @Inject
    PortfolioUserMapper portfolioUserMapper;

    public PortfolioUserVO findByUsername(final String username) {
        return portfolioUserMapper.toVO(findEntityByUsername(username));
    }

    public PortfolioUserVO create(final NewPortfolioUserVO newVO, final String username) {
        validator.validate(newVO, username);

        final User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        final Role portfolioUserRole = roleRepository.findByName(ERole.PORTFOLIO_USER).orElseThrow(() -> new ResourceNotFoundException("Role", "name", ERole.PORTFOLIO_USER.name()));
        user.addRole(portfolioUserRole);
        user.persist();


        PortfolioUser portfolioUser = portfolioUserMapper.toEntity(newVO);
        portfolioUser.setUser(user);
        portfolioUser.persist();

        return portfolioUserMapper.toVO(portfolioUser);
    }

    private PortfolioUser findEntityByUsername(final String username) {
        return portfolioUserRepository.findByUserUsername(username).orElseThrow(() -> new ResourceNotFoundException("PortfolioUser", "username", username));
    }
}
