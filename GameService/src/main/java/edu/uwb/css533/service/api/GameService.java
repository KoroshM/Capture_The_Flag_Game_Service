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
    public Response startGame(@QueryParam("session_id") int sid, @QueryParam("user_id") int uid) {
        Integer numPlayers = dao.checkNumPlayers(sid);
        if(numPlayers == null) {
            return Response.ok("This session does not exist, game could not be started.").build();
        } else if(numPlayers == 2) {
            try {
                Response flagResponse = getFlag(sid);
                String flagName = dao.getFlag(sid);
                String feature1 = dao.getFeature1(sid);
                String feature2 = dao.getFeature2(sid);
                String feature3 = dao.getFeature3(sid);

                dao.updateGameStatus(true, sid);
                dao.updatePlayerGameStatus(true, uid);
                return Response.ok("The game has begun. The flag you are capturing is " + flagName + ". " +
                        "The features you need are: " + feature1 + ", " + feature2 + ", and " + feature3 + ".").build();
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
    public Response checkStartGame(@QueryParam("session_id") int sid, @QueryParam("user_id") int uid) {
        Boolean status = dao.getGameStatus(sid);
        if(status == true) {
            try {
                String flagName = dao.getFlag(sid);
                String feature1 = dao.getFeature1(sid);
                String feature2 = dao.getFeature2(sid);
                String feature3 = dao.getFeature3(sid);
                dao.updatePlayerGameStatus(true, uid);
                return Response.ok("The game has begun. The flag you are capturing is " + flagName + "." +
                        "The features you need are: " + feature1 + ", " + feature2 + ", and " + feature3 + ".").build();
            } catch (Exception e) {
                return Response.ok(e.getMessage()).build();
            }
        } else {
            return Response.ok("The game has not begun.").build();
        }
    }

    @GET
    @Path("/check_feature")
    public Response checkFeature(@QueryParam("session_id") int sid, @QueryParam("user_id") int uid, @QueryParam("feature") String feature) {

        String feature1 = dao.getFeature1(sid);
        String feature2 = dao.getFeature2(sid);
        String feature3 = dao.getFeature3(sid);

        Integer p1_id = dao.getPlayer1ID(sid);
        Integer p2_id = dao.getPlayer2ID(sid);
        Integer winner = dao.getWinner(sid);

        if (winner == -1) {
            if (feature.equals(feature1) || feature.equals(feature2) || feature.equals(feature3)) {
                try {
                    Integer progress1 = dao.getPlayer1Progress(sid);
                    Integer progress2 = dao.getPlayer2Progress(sid);
                    if (uid == p1_id) {
                        progress1++;
                        dao.updatePlayer1Progress(progress1, sid);
                        if (progress1 == 3) {
                            dao.updateWinner(p1_id, sid);
                            return Response.ok("Player 1 won the game!").build();
                        } else {
                            return Response.ok("You have collected " + progress1 + " features.").build();
                        }
                    } else {
                        progress2++;
                        dao.updatePlayer2Progress(progress2, sid);
                        if (progress2 == 3) {
                            dao.updateWinner(p2_id, sid);
                            return Response.ok("Player 2 won the game!").build();
                        } else {
                            return Response.ok("You have collected " + progress2 + " features.").build();
                        }
                    }
                } catch (Exception e) {
                    return Response.ok(e.getMessage()).build();
                }
            } else {
                return Response.ok("This is not a feature of the " + dao.getFlag(sid) + " flag.").build();
            }
        } else {
            if(winner == p1_id) {
                return Response.ok("Player 1 won the game.").build();
            } else {
                return Response.ok("Player 2 won the game.").build();
            }
        }

    }


    @GET
    @Path("/end_game")
    public Response checkFeature(@QueryParam("session_id") int sid) {

        if(dao.getWinner(sid) != -1) {
            Integer player1_id = dao.getPlayer1ID(sid);
            Integer player2_id = dao.getPlayer2ID(sid);
            try {
                dao.updatePlayerGameStatus(false, player1_id);
                dao.updatePlayerGameStatus(false, player2_id);
                dao.endPlayerActiveSession(-1, player1_id);
                dao.endPlayerActiveSession(-1, player2_id);
                return Response.ok("Game has ended.").build();
            } catch (Exception e) {
                return Response.ok(e.getMessage()).build();
            }
        } else {
            return Response.ok("Game is still ongoing.").build();
        }
    }
}
