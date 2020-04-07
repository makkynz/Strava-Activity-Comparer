package stravacustom.webapi;

import com.sun.jersey.json.impl.provider.entity.JSONArrayProvider;
import org.json.JSONArray;
import org.json.JSONObject;
import stravacustom.data.AthleteRepository;
import stravacustom.domain.entities.Athlete;
import stravacustom.utils.JsonHelper;

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

        JSONArray output = new JSONArray();

        for (Athlete a : AthleteRepository.getAllAthletes()) {
            output.put(a.getSummaryJson());
        }

        String out = output.toString();

        return Response.ok(out, MediaType.APPLICATION_JSON).build();
    }
}