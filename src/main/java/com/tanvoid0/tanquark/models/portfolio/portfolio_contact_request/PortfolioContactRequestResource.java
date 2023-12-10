package com.tanvoid0.tanquark.models.portfolio.portfolio_contact_request;

import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
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
@Path("/api/contact")
@RequiredArgsConstructor
@Slf4j
public class PortfolioContactRequestResource {

    private final PortfolioContactRequestService service;

    @POST
    @Path("/")
    @PermitAll
    public Response contactMe(@RequestBody(required = true) @Valid final NewPortfolioContactRequestVO request) {
        final PortfolioContactRequestResponseVO response = service.request(request);
        return Response.ok().build();
    }
}
