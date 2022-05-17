package edu.uwb.css533.service.api;

import edu.uwb.css533.service.db.GameDao;
import org.jdbi.v3.core.Jdbi;

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
import java.util.Random;

@Path("/capture_the_flag")
public class GameService {

    private Jdbi jdbi;
    private GameDao dao;
    private static HttpClient HTTP_CLIENT;

    public GameService(Jdbi jdbi, GameDao dao) {
        this.jdbi = jdbi;
        this.dao = dao;

        HTTP_CLIENT = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    @GET
    @Path("/start_game")
    public Response startGame(@QueryParam("session_id") int id) {
        Integer numPlayers = dao.checkNumPlayers(id);
        if(numPlayers >= 2) {
            try {
                Response flagName = getFlag(id);
                dao.updateGameStatus(true, id);
                return Response.ok("The game has begun. The flag you are capturing is " + flagName + ".").build();
            } catch (Exception e) {
                return Response.ok(e.getMessage()).build();
            }
        } else {
            return Response.ok("The game could not begin. Please add more players").build();
        }
    }

    public HttpRequest requestFlag(int id) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8060/flag/get_flag?session_id=" + id))
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();
    }

    public Response getFlag(int id) throws Exception {
        HttpRequest request = requestFlag(id);
        String response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return Response.ok(response).build();

    }

    @GET
    @Path("/check_game")
    public Response checkStartGame(@QueryParam("session_id") int id) {
        Boolean status = dao.getGameStatus(id);
        if(status == true) {
            try {
                String flagName = dao.getFlag(id);
                return Response.ok("The game has begun. The flag you are capturing is " + flagName + ".").build();
            } catch (Exception e) {
                return Response.ok(e.getMessage()).build();
            }
        } else {
            return Response.ok("The game has not begun.").build();
        }
    }
}
