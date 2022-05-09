package edu.uwb.css533.service.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.net.http.HttpClient;

@Path("/capture_the_flag")
public class FlagResource {
    private static HttpClient HTTP_CLIENT;

    public FlagResource() {
        HTTP_CLIENT = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    @GET
    @Path("/new_flag")
    public Response createNewUser(@QueryParam("user_id") String id) {

    }

    @GET
    @Path("/flag_features")
    public Response createNewUser(@QueryParam("user_id") String id, @QueryParam("flag_id") String flag_id) {

    }
}
