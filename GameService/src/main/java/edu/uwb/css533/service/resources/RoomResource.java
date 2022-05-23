package edu.uwb.css533.service.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

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
    public Response createNewRoom(@QueryParam("user_id") int id) {
        try {
            return getNewRoom(id);
        } catch (Exception e) {
            //return Response.ok("Exception thrown" + e.getMessage()).build();
            return Response.ok(-1).build();
        }
    }

    public HttpRequest requestNewRoom(int id) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI("http://127.0.01:8070/room/new?user_id=" + id))
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();
    }

    public Response getNewRoom(int id) throws Exception {
        HttpRequest request = requestNewRoom(id);
        String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return Response.ok(response).build();

    }

    @GET
    @Path("/join_room")
    public Response joinExistingRoom(@QueryParam("user_id") int uid, @QueryParam("session_id") int sid) {

        try {
            return getJoinRoom(uid, sid);
        } catch (Exception e) {
            //return Response.ok("Exception thrown" + e.getMessage()).build();
            return Response.ok(-1).build();
        }
    }

    public HttpRequest requestJoinRoom(int id, int sid) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI("http://127.0.01:8070/room/join?user_id=" + id + "&session_id=" + sid))
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();
    }

    public Response getJoinRoom(int id, int sid) throws Exception {
        HttpRequest request = requestJoinRoom(id, sid);
        String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return Response.ok(response).build();

    }
}
