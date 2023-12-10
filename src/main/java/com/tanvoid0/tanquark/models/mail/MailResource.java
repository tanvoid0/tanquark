package com.tanvoid0.tanquark.models.mail;

import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/mail")
@RequiredArgsConstructor
@Slf4j
public class MailResource {

    private final MailService service;

    @POST
    @Path("/")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public Response sendMail() {
//        log.info("Request to send mail: {}", request);
        log.info("Request to send mail");
        service.sendMail();
        return Response.ok("Email sent").build();
    }
}
