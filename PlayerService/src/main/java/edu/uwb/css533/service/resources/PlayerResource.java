package edu.uwb.css533.service.resources;

import edu.uwb.css533.service.db.PlayerDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/player")
//@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource {

    private Jdbi jdbi;
    private PlayerDao dao;
    private int user_id;
    private boolean logged_in;

    public PlayerResource(Jdbi jdbi, PlayerDao dao) {
        this.jdbi = jdbi;
        this.dao = dao;
    }

    @GET
    @Path("/create_new_user")
    public Response createNewUser(@QueryParam("username") String username, @QueryParam("password") String password) {

        Integer result = dao.findUserIdByUsername(username);
        if(result == null) {
            dao.insert(username, password, user_id++, false);
            return Response.ok("User created.").build();
        } else {
            return Response.ok("User already exists.").build();
        }
    }

    @GET
    @Path("/login")
    public Response login(@QueryParam("username") String username, @QueryParam("password") String password) {

        Integer result = dao.findUserIdByUsername(username);
        if (result == null) {
            return Response.ok("User does not exist.").build();
        } else {
            String correctPassword = dao.findPasswordByUserId(result);
            int compare = correctPassword.compareTo(password);
            if(compare != 0) {
                return Response.ok("Incorrect Password.").build();
            } else {
                dao.logIn(true, result);
                return Response.ok("Successfully logged in.").build();
            }
        }
    }
}
