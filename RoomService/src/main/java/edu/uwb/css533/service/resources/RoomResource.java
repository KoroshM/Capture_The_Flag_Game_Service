package edu.uwb.css533.service.resources;

import edu.uwb.css533.service.db.RoomDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/room")
public class RoomResource {
    private Jdbi jdbi;
    private RoomDao dao;
    private int session_id;

    public RoomResource(Jdbi jdbi, RoomDao dao) {
        this.jdbi = jdbi;
        this.dao = dao;
        session_id = 0;
    }

    @GET
    @Path("/new")
    public Response createNewRoom(@QueryParam("user_id") int uid) {
        try {
            String passwordCheck = dao.findPasswordByUserId(uid);
            if(passwordCheck != null) {
                dao.insert(session_id++, false, -1, null,
                        null, -1, null, -1, null,
                        -1, 1, uid, -1, 0,
                        0, -1, -1);
                int correctPrint = session_id - 1;
                return Response.ok(correctPrint).build();
            } else {
                return Response.ok(-1).build();
            }

        } catch (Exception e) {
            //return Response.ok(e.getMessage()).build();
            return Response.ok(-1).build();
        }
    }


    @GET
    @Path("/join")
    public Response joinRoom(@QueryParam("user_id") int uid, @QueryParam("session_id") int sid) {
        Integer numPlayers = dao.checkNumPlayers(sid);
        try {
            //session id does not exist
            if (numPlayers == null) {
                return Response.ok(-1).build();
            //there are already two (max) players in the session
            } else if (numPlayers == 2) {
                return Response.ok(-1).build();
            } else {
                dao.updateNumPlayers(2, sid);
                dao.updatePlayer2ID(uid, sid);
                return Response.ok(sid).build();
            }
        } catch (Exception e) {
            //return Response.ok(e.getMessage()).build();
            return Response.ok(-1).build();
        }
    }
}
