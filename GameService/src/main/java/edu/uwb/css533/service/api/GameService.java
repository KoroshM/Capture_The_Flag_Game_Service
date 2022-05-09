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

    public GameService(Jdbi jdbi, GameDao dao) {
        this.jdbi = jdbi;
        this.dao = dao;

    }

    @GET
    @Path("/start_game")
    public Response startGame(@QueryParam("session_id") int id) {
        Integer numPlayers = dao.checkNumPlayers(id);
        if(numPlayers >= 2) {
            Random rand = new Random();
            int randNum = rand.nextInt();
            String flagName = dao.getFlag(randNum);
            dao.updateFlag(flagName, id);
            return Response.ok("Game has begun. Here is your flag and target codes.").build();
        } else {
            return Response.ok("Game could not begin. Please add more players").build();
        }

    }
}
