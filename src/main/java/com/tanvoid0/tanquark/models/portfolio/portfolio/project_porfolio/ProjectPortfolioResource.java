package com.tanvoid0.tanquark.models.portfolio.portfolio.project_porfolio;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.util.List;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/portfolio/project")
@RolesAllowed({"USER", "PORTFOLIO_USER"})
@RequiredArgsConstructor
@Slf4j
public class ProjectPortfolioResource {

    private final ProjectPortfolioService portfolioService;

    @GET
    @Path("/")
    public Response get() {
        final List<ProjectPortfolioVO> response = portfolioService.findAll();
        return Response.ok(response).build();
    }

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

    @PUT
    @Path("/{id}/images")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response attachImages(@PathParam("id") long projectId, @MultipartForm MultipartFormDataInput request) {
        log.info("Attaching images to project with id {}", projectId);
        final ProjectPortfolioVO response = portfolioService.attachImages(projectId, request);
        return Response.ok(response).build();
    }
}
