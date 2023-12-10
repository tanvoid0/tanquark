package com.tanvoid0.tanquark.models.file;

import io.vertx.ext.web.RoutingContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.nio.file.NoSuchFileException;

@Path("/file")
@Consumes("multipart/form-data")
@RequiredArgsConstructor
public class FileResource {
    private final FileService fileService;
    private final RoutingContext routingContext;

    @POST
    @Path("/write")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response fileUpload(@MultipartForm MultipartFormDataInput input) {
        final String serverUrl = (routingContext.request().sslSession() == null ? "http://" : "https://") + routingContext.request().authority().host() + ":" + routingContext.request().authority().port();
        final FileItemVO result = fileService.uploadFile(input, serverUrl);
        return Response.ok().entity(result.getWebUrl()).build();
    }

    @GET
    @Path("/read/{fileType}/{fileName}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response fileRead(@PathParam("fileType") final String fileType, @PathParam("fileName") final String fileName) {
        try {
            return Response.ok().entity(fileService.readFile(fileType, fileName)).header("Content-Type", "image/png").build();
        } catch (NoSuchFileException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
