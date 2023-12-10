package com.tanvoid0.tanquark.models.portfolio.portfolio_contact_request;

import com.tanvoid0.tanquark.common.exception.ResourceNotFoundException;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@ApplicationScoped
@RequiredArgsConstructor
public class PortfolioContactRequestService {

    private final PortfolioContactRequestMapper portfolioContactRequestMapper;

    private final PortfolioContactRequestRepository repository;

    private final PortfolioUserRepository portfolioUserRepository;

    public PortfolioContactRequestResponseVO request(final NewPortfolioContactRequestVO newVO) {
        // find portfolio user
        final PortfolioUser portfolioUser = portfolioUserRepository.findByUserUsername(newVO.getPortfolioUserUsername()).orElseThrow(() -> new ResourceNotFoundException("PortfolioUser", "username", newVO.getPortfolioUserUsername()));

        final PortfolioContactRequest entity = portfolioContactRequestMapper.toEntity(newVO);
        entity.setPortfolioUser(portfolioUser);
        entity.persist();


        // send welcome & confirmation message with mail
        return PortfolioContactRequestResponseVO.builder()
                .message(String.format("Thanks for contacting. %s will shortly reach you via your email %s.", portfolioUser.getUser().getName(), entity.getEmail()))
                .build();

    }

    public void confirmation() {
        // create temporary token

        // send mail with temporarytoken
    }
}
