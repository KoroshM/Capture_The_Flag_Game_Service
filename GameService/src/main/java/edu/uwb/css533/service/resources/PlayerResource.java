package edu.uwb.css533.service.resources;

import edu.uwb.css533.service.UserDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// http://server:port/path?param=something

//path (part of URL)
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource {

    private Jdbi jdbi;
    private UserDao dao;
    private int user_id;

    public PlayerResource(Jdbi jdbi, UserDao dao) {
        this.jdbi = jdbi;
        this.dao = dao;
    }

    @GET
    @Path("/create_new_user")
    public Response createNewUser(@QueryParam("username") String username, @QueryParam("password") String password) {

        Integer result = dao.findUserIdByUsername(username);
        if(result == null) {
            dao.insert(username, password, user_id++);
            return Response.ok("User created.").build();
        } else {
            return Response.ok("User already exists. " + result).build();
        }

    }

    @GET
    @Path("/login")
    public Response login(@QueryParam("username") String username, @QueryParam("password") String password) {

        Integer result = dao.findUserIdByUsername(username);
        if(result == null) {
            return Response.ok("User does not exist.").build();
        } else {
            return Response.ok("Successfully logged in. " + result).build();
        }

    }

}
