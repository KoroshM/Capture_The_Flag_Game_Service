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
        dao.insert(null, session_id++, 1);
        return Response.ok(session_id).build();
    }


    @GET
    @Path("/join")
    public Response joinRoom(@QueryParam("user_id") int id, @QueryParam("session_id") int sid) {
        Integer numPlayers = dao.checkNumPlayers(sid);
        if(numPlayers == null) {
            return Response.ok("Player cannot join " + sid + ". The session does not exist.").build();
        } else {
            dao.updateNumPlayers(numPlayers++, sid);
            return Response.ok("Player joined " + sid + ".").build();
        }
    }
}
