package com.tanvoid0.tanquark.models.portfolio.portfolio.project_porfolio;

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
@Path("/api/portfolio/project")
@RolesAllowed({"USER", "PORTFOLIO_USER"})
@RequiredArgsConstructor
public class ProjectPortfolioResource {

    private final ProjectPortfolioService portfolioService;

    @POST
    @Path("/")
    public Response create(@RequestBody final NewProjectPortfolioVO request) {
        final ProjectPortfolioVO response = portfolioService.add(request);

        return Response.ok(response).status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/batch")
    public Response create(@RequestBody final List<NewProjectPortfolioVO> request) {
        final List<ProjectPortfolioVO> response = portfolioService.add(request);
        return Response.ok(response).status(Response.Status.CREATED).build();
    }
}
