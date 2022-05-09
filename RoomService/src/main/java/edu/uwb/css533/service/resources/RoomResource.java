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
    }

    @GET
    @Path("/new")
    public Response createNewRoom(@QueryParam("user_id") String id) {
        return Response.ok("not done").build();
    }


    @GET
    @Path("/join")
    public Response joinRoom(@QueryParam("user_id") String id) {
        return Response.ok("not done").build();
    }
}
