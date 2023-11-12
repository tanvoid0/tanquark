package com.tanvoid0.tanquark.models.portfolio.hobby;

import com.tanvoid0.tanquark.common.base.SwapOrderSequence;
import com.tanvoid0.tanquark.config.auth.AuthService;
import com.tanvoid0.tanquark.models.portfolio.PortfolioUser;
import com.tanvoid0.tanquark.models.portfolio.hobby.vo.HobbyVO;
import com.tanvoid0.tanquark.models.portfolio.hobby.vo.NewHobbyVO;
import com.tanvoid0.tanquark.models.portfolio.hobby.vo.UpdateHobbyVO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.List;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/hobby")
@RolesAllowed({"USER", "PORTFOLIO_USER"})
public class HobbyResource {

    @Inject
    HobbyService hobbyService;

    @Inject
    AuthService authService;

    @GET
    @Path("/")
    public Response getHobbiesByUser() {
        final PortfolioUser user = authService.getAuthenticatedPortfolioUser();
        final List<HobbyVO> response = hobbyService.findHobbyByUserId(user.getId());
        return Response.ok(response).build();
    }

    @GET
    @Path("/{id}")
    public Response getHobby(@PathParam("id") final long id) {
        final HobbyVO response = hobbyService.findById(id);
        return Response.ok(response).build();
    }

    @POST
    @Path("/")
    public Response addHobby(@RequestBody(required = true) final NewHobbyVO newVO) {
        final HobbyVO response = hobbyService.addHobby(newVO);
        return Response.ok(response).status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/batch")
    public Response addHobbies(@RequestBody(required = true) final List<NewHobbyVO> newVOs) {
        final List<HobbyVO> response = hobbyService.addHobby(newVOs);
        return Response.ok(response).build();
    }

    @PUT
    @Path("/swap")
    public Response swapHobbies(@RequestBody(required = true) final SwapOrderSequence request) {
        final List<HobbyVO> response = hobbyService.swap(request);
        return Response.ok(response).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") final long id, @RequestBody(required = true) final UpdateHobbyVO request) {
        request.setId(id);
        final HobbyVO response = hobbyService.update(request);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") final long id) {
        hobbyService.delete(id);
        return Response.noContent().build();
    }

}
