package com.tanvoid0.tanquark.models.user;

import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUserRepository;
import com.tanvoid0.tanquark.models.portfolio.hobby.Hobby;
import com.tanvoid0.tanquark.models.portfolio.hobby.HobbyRepository;
import com.tanvoid0.tanquark.models.user.role.ERole;
import com.tanvoid0.tanquark.models.user.role.Role;
import com.tanvoid0.tanquark.models.user.role.RoleRepository;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@Transactional
class RepositoryTest {


    @Inject
    EntityManager entityManager;

    @Inject
    RoleRepository roleRepository;

    @Inject
    PortfolioUserRepository portfolioUserRepository;

    @Inject
    HobbyRepository hobbyRepository;


    private Role role;
    private User user;
    private PortfolioUser portfolioUser;
    private Hobby hobby;

    @BeforeEach
    private void setup() {
        PanacheMock.mock(PanacheEntity.class);
    }


    @Test
    void portfolioUserRepository_findByUserUsername() {
        portfolioUserSetup();

        final Optional<PortfolioUser> portfolioUserOptional = portfolioUserRepository.findByUserUsername(user.getUsername());
        assertThat(portfolioUserOptional).isPresent();
        assertThat(portfolioUserOptional.get().getUser().getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    void hobbyRepository_findHobbyByUser() {
        hobbySetup();

        final List<Hobby> hobbies = hobbyRepository.findAllByUserId(user.getId());

        assertThat(hobbies).hasSize(1);
        assertThat(hobbies.get(0).getTitle()).isEqualTo("Travelling");
    }

    void roleSetup() {
        role = roleRepository.findByName(ERole.USER).get();
        entityManager.persist(role);
        assertThat(role).isNotNull();
    }

    void userSetup() {
        roleSetup();
        user = new User();
        user.setEmail("test@mail.com");
        user.setName("user");
        user.setRoles(Set.of(role));
        user.setUsername("user");
        user.setPassword("Random");
        entityManager.persist(user);
        assertThat(user.getId()).isNotNull();
        assertThat(user.getUsername()).isNotNull();
    }

    void portfolioUserSetup() {
        userSetup();
        portfolioUser = new PortfolioUser();
        portfolioUser.setUser(user);
        entityManager.persist(portfolioUser);
        assertThat(portfolioUser.getId()).isNotNull();
    }

    void hobbySetup() {
        portfolioUserSetup();
        hobby = new Hobby();
        hobby.setTitle("Travelling");
        hobby.setPortfolioUser(portfolioUser);
        entityManager.persist(hobby);
        assertThat(hobby.getId()).isNotNull();
    }
}