package com.tanvoid0.tanquark.models.portfolio;

import com.tanvoid0.tanquark.config.auth.AuthService;
import com.tanvoid0.tanquark.models.user.User;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
@Path("/api/portfolio")
public class PortfolioUserResource {

    @Inject
    PortfolioUserService portfolioUserService;

    @Inject
    AuthService authService;

    @GET
    @Path("/user/{username}")
    @PermitAll
    // Make use of token
    public Response getPortfolioUser(@PathParam("username") final String username) {
        return Response.ok(portfolioUserService.findByUsername(username)).build();
    }

    @POST
    @Path("/")
    @RolesAllowed("USER")
    public Response create(@RequestBody(required = true) final NewPortfolioUserVO request) {
        final User user = authService.getAuthenticatedUser();
        return Response.ok(portfolioUserService.create(request, user.getUsername())).build();
    }


}
