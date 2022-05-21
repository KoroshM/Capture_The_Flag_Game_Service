package edu.uwb.css533.service.resources;

import edu.uwb.css533.service.db.RoomDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Random;

@Path("/room")
public class RoomResource {
    private Jdbi jdbi;
    private RoomDao dao;
    private int session_id;

    public RoomResource(Jdbi jdbi, RoomDao dao) {
        this.jdbi = jdbi;
        this.dao = dao;
    }

    @GET
    @Path("/new")
    public Response createNewRoom(@QueryParam("user_id") int id) {
        dao.insert(session_id++, false, null, null, null, null, 1, id, -1, false, false, false, false, false, false);
        int correctPrint = session_id - 1;
        return Response.ok("Your room ID is " + correctPrint + ".").build();
    }


    @GET
    @Path("/join")
    public Response joinRoom(@QueryParam("user_id") int id, @QueryParam("session_id") int sid) {
        Integer numPlayers = dao.checkNumPlayers(sid);
        if(numPlayers == null) {
            return Response.ok("Player cannot join room " + sid + ". The session does not exist.").build();
        } else if (numPlayers == 2) {
            return Response.ok("Player cannot join room " + sid + ". The session is full.").build();
        } else {
            dao.updateNumPlayers(2, sid);
            dao.updatePlayer2ID(id, sid);
            return Response.ok("Player joined room " + sid + ".").build();
        }
    }

    @GET
    @Path("/check_feature_code")
    public Response checkCode(@QueryParam("feature_code") int code, @QueryParam("session_id") int id) {
        return Response.ok("not done").build();
    }
}
