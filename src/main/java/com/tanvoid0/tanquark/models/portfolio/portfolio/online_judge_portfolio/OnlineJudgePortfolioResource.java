package com.tanvoid0.tanquark.models.portfolio.portfolio.online_judge_portfolio;

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
@Path("/api/portfolio/online_judge")
@RolesAllowed({"USER", "PORTFOLIO_USER"})
@RequiredArgsConstructor
public class OnlineJudgePortfolioResource {

    private final OnlineJudgePortfolioService onlineJudgePortfolioService;

    @POST
    @Path("/")
    public Response create(@RequestBody final NewOnlineJudgePortfolioVO request) {
        final OnlineJudgePortfolioVO response = onlineJudgePortfolioService.add(request);

        return Response.ok(response).status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/batch")
    public Response create(@RequestBody final List<NewOnlineJudgePortfolioVO> request) {
        final List<OnlineJudgePortfolioVO> response = onlineJudgePortfolioService.add(request);
        return Response.ok(response).status(Response.Status.CREATED).build();
    }
}
