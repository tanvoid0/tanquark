package com.tanvoid0.tanquark.models.portfolio.skill_group.hard;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.List;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/skill/hard")
@RolesAllowed({"USER", "PORTFOLIO_USER"})
@RequiredArgsConstructor
public class SkillHardResource {
    private final SkillHardService skillHardService;

    @POST
    @Path("/")
    public Response create(@RequestBody final NewSkillHardVO request) {
        final SkillHardVO skill = skillHardService.add(request);

        return Response.ok(skill).status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/batch")
    public Response create(@RequestBody final List<NewSkillHardVO> request) {
        final List<SkillHardVO> skills = skillHardService.add(request);
        return Response.ok(skills).status(Response.Status.CREATED).build();
    }
}
