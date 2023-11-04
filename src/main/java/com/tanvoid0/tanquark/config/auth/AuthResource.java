package com.tanvoid0.tanquark.config.auth;

import com.tanvoid0.tanquark.config.auth.vo.AuthenticatedUserVO;
import com.tanvoid0.tanquark.config.auth.vo.LoginRequestVO;
import com.tanvoid0.tanquark.config.auth.vo.RegisterRequestVO;
import io.quarkus.security.Authenticated;
import io.quarkus.security.UnauthorizedException;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.security.Principal;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class AuthResource {

    @Inject
    AuthService authService;

    @GET
    @Path("/test")
//    @RolesAllowed("User")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Hello";
    }

    @POST
    @Path("/authenticate")
    @Authenticated
//    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(@Context SecurityContext context) {
        final Principal caller = context.getUserPrincipal();
//        final String name = caller == null ? "anonymous" : caller.getName();
        // hello user@mail.com, isSecure: false, authScheme: Bearer
//        final String response = String.format("hello %s, isSecure: %s, authScheme: %s", name, context.isSecure(), context.getAuthenticationScheme());
        if (caller == null || caller.getName() == null || caller.getName().isEmpty()) {
            throw new UnauthorizedException("User email is not provided");
        }
        final AuthenticatedUserVO userVO = authService.authenticate(caller.getName());
        return Response.ok(userVO).build();
    }

    @POST
    @Path("/register")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(@RequestBody(required = true) final RegisterRequestVO request) {
        final AuthenticatedUserVO user = authService.register(request);
        return Response.ok(user).build();
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(
            @RequestBody(required = true)
            LoginRequestVO request) {
        return Response.ok(authService.login(request)).build();
    }

    @POST
    @Path("/token")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response generateAuthenticationToken(@RequestBody(required = true) final LoginRequestVO request) {
        return Response.ok(authService.generateToken(request)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") final long id) {
        authService.remove(id);
        return Response.noContent().build();
    }
}
