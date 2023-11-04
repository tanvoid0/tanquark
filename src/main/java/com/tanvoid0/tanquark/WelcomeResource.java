package com.tanvoid0.tanquark;

import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("")
@PermitAll
public class WelcomeResource {
    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public Response welcome() {
        return Response.ok("Welcome to TanQuark").build();
    }
}
