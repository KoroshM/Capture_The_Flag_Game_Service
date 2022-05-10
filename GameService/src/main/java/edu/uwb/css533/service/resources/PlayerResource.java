package edu.uwb.css533.service.resources;

import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

// http://server:port/path?param=something

//path (part of URL)
@Path("/capture_the_flag")
//@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource {
    private int user_id;
    private static HttpClient HTTP_CLIENT;


    public PlayerResource() {
        HTTP_CLIENT = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    @GET
    @Path("/new_user")
    public Response createNewUser(@QueryParam("username") String username, @QueryParam("password") String password) {
        try {
            return getNewUser(username, password);
        } catch (Exception e) {
            return Response.ok("Exception thrown " + e.getMessage()).build();
        }

    }

    public HttpRequest requestNewUser(String username, String password) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8090/player/create_new_user?username=" + username
                        + "&password=" + password))
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();
    }

    public Response getNewUser(String username, String password) throws Exception {
        HttpRequest request = requestNewUser(username, password);
        String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return Response.ok(response).build();

    }

    @GET
    @Path("/login")
    public Response login(@QueryParam("username") String username, @QueryParam("password") String password) {
        try {
            return getLogIn(username, password);
        } catch (Exception e) {
            return Response.ok("Exception thrown " + e.getMessage()).build();
        }
    }

    public HttpRequest requestLogIn(String username, String password) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8090/player/login?username=" + username
                        + "&password=" + password))
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();
    }

    public Response getLogIn(String username, String password) throws Exception {
        HttpRequest request = requestLogIn(username, password);
        String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return Response.ok(response).build();

    }

}