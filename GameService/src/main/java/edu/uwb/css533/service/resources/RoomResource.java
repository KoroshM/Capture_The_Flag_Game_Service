package edu.uwb.css533.service.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.net.http.HttpClient;

@Path("/capture_the_flag")
public class RoomResource {

    private static HttpClient HTTP_CLIENT;

    public RoomResource() {
        HTTP_CLIENT = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    @GET
    @Path("/new_room")
    public Response createNewRoom(@QueryParam("user_id") String id) {
        return Response.ok("not done").build();
    }

    @GET
    @Path("/join_room")
    public Response joinExistingRoom(@QueryParam("user_id") String id) {
        return Response.ok("not done").build();
    }
}
