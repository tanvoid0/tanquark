package com.tanvoid0.tanquark.models.portfolio;

import com.tanvoid0.tanquark.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanquark.models.portfolio.migration.NewPortfolioUserMigrationVO;
import com.tanvoid0.tanquark.models.portfolio.portfolio_contact_request.NewPortfolioContactRequestVO;
import com.tanvoid0.tanquark.models.user.User;
import com.tanvoid0.tanquark.models.user.UserRepository;
import com.tanvoid0.tanquark.models.user.role.ERole;
import com.tanvoid0.tanquark.models.user.role.Role;
import com.tanvoid0.tanquark.models.user.role.RoleRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@Transactional
@ApplicationScoped
@RequiredArgsConstructor
public class PortfolioUserService {

    private final PortfolioUserRepository portfolioUserRepository;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PortfolioUserValidator validator;

    private final PortfolioUserMapper portfolioUserMapper;
    private final ModelMapper mapper = new ModelMapper();

    public PortfolioUserVO findByUsernameFull(final String username) {
        return portfolioUserMapper.toVO(findEntityByUsername(username));
    }

    public PortfolioUserVO findByUsernameSlim(final String username) {
        return portfolioUserMapper.toSecretVO(findEntityByUsername(username));
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

    public PortfolioUserVO migrate(final NewPortfolioUserMigrationVO request, final String username) {
        final Optional<PortfolioUser> user = portfolioUserRepository.findByUserUsername(username);

        PortfolioUser entity;
        if (user.isPresent()) {
            entity = user.get();
            final UpdatePortfolioUserVO updateVO = mapper.map(request, UpdatePortfolioUserVO.class);
            mapper.map(updateVO, entity);
        } else {
            final NewPortfolioUserVO newPortfolioUserVO = mapper.map(request, NewPortfolioUserVO.class);
            entity = portfolioUserMapper.toEntity(newPortfolioUserVO);
        }
        entity.persistAndFlush();
        return portfolioUserMapper.toVO(entity);
    }

    public void contactRequest(final NewPortfolioContactRequestVO request) {

    }

    private PortfolioUser findEntityByUsername(final String username) {
        return portfolioUserRepository.findByUserUsername(username).orElseThrow(() -> new ResourceNotFoundException("PortfolioUser", "username", username));
    }
}
