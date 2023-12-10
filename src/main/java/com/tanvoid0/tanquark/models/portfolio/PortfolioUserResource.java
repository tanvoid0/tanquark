package com.tanvoid0.tanquark.models.portfolio;

import com.tanvoid0.tanquark.config.auth.AuthService;
import com.tanvoid0.tanquark.models.user.User;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
@Path("/api/portfolio")
@RequiredArgsConstructor
@Slf4j
public class PortfolioUserResource {

    private final PortfolioUserService portfolioUserService;

    private final AuthService authService;

    @GET
    @Path("/user/{username}")
    @PermitAll
    public Response getPortfolioUser(@PathParam("username") final String username) {
        log.debug("Request to get portfolio for user {}", username);
        final PortfolioUserVO userVO = portfolioUserService.findByUsername(username);
        return Response.ok(userVO).build();
    }

    @POST
    @Path("/")
    @RolesAllowed("USER")
    public Response create(@RequestBody(required = true) final NewPortfolioUserVO request) {
        final User user = authService.getAuthenticatedUser();
        return Response.ok(portfolioUserService.create(request, user.getUsername())).build();
    }

}
