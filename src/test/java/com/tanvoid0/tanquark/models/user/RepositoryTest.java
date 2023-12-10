package com.tanvoid0.tanquark.models.user;

//@QuarkusTest
//@Transactional
class RepositoryTest {

//
//    @Inject
//    EntityManager entityManager;
//
//    @Inject
//    RoleRepository roleRepository;
//
//    @Inject
//    PortfolioUserRepository portfolioUserRepository;
//
//    @Inject
//    HobbyRepository hobbyRepository;
//
//
//    private Role role;
//    private User user;
//    private PortfolioUser portfolioUser;
//    private Hobby hobby;
//
//    @BeforeEach
//    private void setup() {
//        PanacheMock.mock(PanacheEntity.class);
//    }
//
//
//    @Test
//    @Disabled
//    void portfolioUserRepository_findByUserUsername() {
//        portfolioUserSetup();
//
//        final Optional<PortfolioUser> portfolioUserOptional = portfolioUserRepository.findByUserUsername(user.getUsername());
//        assertThat(portfolioUserOptional).isPresent();
//        assertThat(portfolioUserOptional.get().getUser().getUsername()).isEqualTo(user.getUsername());
//    }
//
//    @Test
//    @Disabled
//    void hobbyRepository_findHobbyByUser() {
//        hobbySetup();
//
//        final List<Hobby> hobbies = hobbyRepository.findAllByUserId(user.getId());
//
//        assertThat(hobbies).hasSize(1);
//        assertThat(hobbies.get(0).getTitle()).isEqualTo("Travelling");
//    }
//
//    void roleSetup() {
//        role = roleRepository.findByName(ERole.USER).get();
//        entityManager.persist(role);
//        assertThat(role).isNotNull();
//    }
//
//    void userSetup() {
//        roleSetup();
//        user = new User();
//        user.setEmail("test@mail.com");
//        user.setName("user");
//        user.setRoles(Set.of(role));
//        user.setUsername("user");
//        user.setPassword("Random");
//        entityManager.persist(user);
//        assertThat(user.getId()).isNotNull();
//        assertThat(user.getUsername()).isNotNull();
//    }
//
//    void portfolioUserSetup() {
//        userSetup();
//        portfolioUser = new PortfolioUser();
//        portfolioUser.setUser(user);
//        entityManager.persist(portfolioUser);
//        assertThat(portfolioUser.getId()).isNotNull();
//    }
//
//    void hobbySetup() {
//        portfolioUserSetup();
//        hobby = new Hobby();
//        hobby.setTitle("Travelling");
//        hobby.setPortfolioUser(portfolioUser);
//        entityManager.persist(hobby);
//        assertThat(hobby.getId()).isNotNull();
//    }
}