package com.tanvoid0.tanquark.config.auth;

import com.tanvoid0.tanquark.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanquark.config.auth.validator.AuthValidator;
import com.tanvoid0.tanquark.config.auth.vo.AuthenticatedUserVO;
import com.tanvoid0.tanquark.config.auth.vo.LoginRequestVO;
import com.tanvoid0.tanquark.config.auth.vo.RegisterRequestVO;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUserRepository;
import com.tanvoid0.tanquark.models.user.User;
import com.tanvoid0.tanquark.models.user.UserRepository;
import com.tanvoid0.tanquark.models.user.mapper.UserMapper;
import com.tanvoid0.tanquark.models.user.role.ERole;
import com.tanvoid0.tanquark.models.user.role.Role;
import com.tanvoid0.tanquark.models.user.role.RoleRepository;
import io.quarkus.security.UnauthorizedException;
import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.Nonnull;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.SecurityContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.Set;

@Singleton
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final AuthValidator validator;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PortfolioUserRepository portfolioUserRepository;

    private final UserMapper userMapper;

    private final SecurityContext securityContext;

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    String issuer;

    // In days
    @ConfigProperty(name = "mp.jwt.timeout")
    int timeout;

    public AuthenticatedUserVO login(final LoginRequestVO request) {
        final User user = validator.validate(request);
        return getAuthenticatedUser(user);
    }

    public AuthenticatedUserVO register(final RegisterRequestVO requestVO) {
        validator.validate(requestVO);
        final User user = userMapper.toEntity(requestVO);
        final Role role = roleRepository.findByName(ERole.USER).orElseThrow(() -> new ResourceNotFoundException("Role", "name", ERole.USER.name()));
        user.setRoles(Set.of(role));
        user.persist();
//        userRepository.persist(user);
        return getAuthenticatedUser(user);
    }

    public User getAuthenticatedUser() {
        final Principal caller = securityContext.getUserPrincipal();
        final String email = caller == null ? null : caller.getName();
        if (email == null || email.isEmpty()) {
            throw new UnauthorizedException("User not provided");
        }
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    public PortfolioUser getAuthenticatedPortfolioUser() {
        final User user = getAuthenticatedUser();
        return portfolioUserRepository.findByUserUsername(user.getUsername()).orElseThrow(() -> new ResourceNotFoundException("PortfolioUser", "username", user.getUsername()));
    }

    public String generateToken(final LoginRequestVO request) {
        final User user = validator.validate(request);
        return generateAuthenticationToken(user.getEmail(), user.getRoles());
    }

    public AuthenticatedUserVO authenticate(@Nonnull final String email) {
        final Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UnauthorizedException("User not found in the database");
        }
        return getAuthenticatedUser(user.get());
    }

    public void remove(final long id) {
        validator.validate(id);
        userRepository.deleteById(id);
    }

    private AuthenticatedUserVO getAuthenticatedUser(final User user) {
        return this.generateAuthenticatedUser(user);
    }

    public AuthenticatedUserVO generateAuthenticatedUser(final User user) {
        AuthenticatedUserVO authenticatedUserVO = userMapper.toAuthenticatedVO(user);
        authenticatedUserVO.setToken(generateAuthenticationToken(user.getEmail(), user.getRoles()));
        authenticatedUserVO.setExpiresAt(expiresAtDateTime());
        return authenticatedUserVO;
    }

    private String generateAuthenticationToken(final String email, final Set<String> roles) {
        log.debug("Issuer Value: {}, user: {}", issuer, email);
        return Jwt.issuer(issuer)
                .upn(email)
                .claim(Claims.email, email)
//                .claim(Claims.birthdate.name(), "2001-07-13")
//                .claim("id", user.getId())
                .groups(roles)
                .expiresAt(expiresAt())
                .sign();
    }

    private long expiresAt() {
        return System.currentTimeMillis() + (long) timeout * 24 * 60 * 60 * 1000;
    }

    private LocalDateTime expiresAtDateTime() {
        return Instant.ofEpochMilli(expiresAt()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
