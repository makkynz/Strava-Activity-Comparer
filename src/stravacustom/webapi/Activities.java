package stravacustom.webapi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/activities")
public class Activities {
    // The Java method will process HTTP GET requests
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@Context UriInfo info) {


        return Response.ok("Hello World").build();
    }
}