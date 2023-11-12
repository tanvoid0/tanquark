package com.tanvoid0.tanquark.models.portfolio.social;

import com.tanvoid0.tanquark.common.base.SwapOrderSequence;
import com.tanvoid0.tanquark.config.auth.AuthService;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.social.vo.NewSocialVO;
import com.tanvoid0.tanquark.models.portfolio.social.vo.SocialVO;
import com.tanvoid0.tanquark.models.portfolio.social.vo.UpdateSocialVO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.List;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/social")
@RolesAllowed({"USER", "PORTFOLIO_USER"})
public class SocialResource {

    @Inject
    SocialService socialService;

    @Inject
    AuthService authService;

    @GET
    @Path("/")
    public Response getHobbiesByUser() {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();
        final List<SocialVO> response = socialService.findHobbyByUserId(user.getId());
        return Response.ok(response).build();
    }

    @GET
    @Path("/{id}")
    public Response getHobby(@PathParam("id") final long id) {
        final SocialVO response = socialService.findById(id);
        return Response.ok(response).build();
    }

    @POST
    @Path("/")
    public Response addHobby(@RequestBody(required = true) final NewSocialVO newVO) {
        final SocialVO response = socialService.addHobby(newVO);
        return Response.ok(response).status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/batch")
    public Response addHobbies(@RequestBody(required = true) final List<NewSocialVO> newVOs) {
        final List<SocialVO> response = socialService.addHobby(newVOs);
        return Response.ok(response).build();
    }

    @PUT
    @Path("/swap")
    public Response swapHobbies(@RequestBody(required = true) final SwapOrderSequence request) {
        final List<SocialVO> response = socialService.swap(request);
        return Response.ok(response).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") final long id, @RequestBody(required = true) final UpdateSocialVO request) {
        request.setId(id);
        final SocialVO response = socialService.update(request);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") final long id) {
        socialService.delete(id);
        return Response.noContent().build();
    }

}
