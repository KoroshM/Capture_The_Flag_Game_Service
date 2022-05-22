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

    public PlayerResource(Jdbi jdbi, PlayerDao dao) {
        this.jdbi = jdbi;
        this.dao = dao;
        user_id = 0;
    }

//    @GET
//    @Path("/create_new_user")
//    public Response createNewUser(@QueryParam("username") String username, @QueryParam("password") String password) {
//
//        Integer result = dao.findUserIdByUsername(username);
//        if(result == null) {
//            dao.insert(username, password, user_id++, false, -1, false);
//            int correctPrint = user_id - 1;
//            return Response.ok("User created with ID = " + correctPrint + ".").build();
//        } else {
//            return Response.ok("User already exists.").build();
//        }
//    }

    @GET
    @Path("/login")
    public Response login(@QueryParam("username") String username, @QueryParam("password") String password) {

        Integer result = dao.findUserIdByUsername(username);
        //If a user tries to log in, and they haven't been created yet, this will create a new user.
        if (result == null) {
            dao.insert(username, password, user_id++, true);
            int correctPrint = user_id - 1;
            return Response.ok(correctPrint).build();
        //If the user does already exist, make sure the password is correct and if it is log them in.
        } else {
            String correctPassword = dao.findPasswordByUserId(result);
            int compare = correctPassword.compareTo(password);
            if(compare != 0) {
                return Response.ok(-1).build();
            } else {
                dao.logIn(true, result);
                return Response.ok(result).build();
            }
        }
    }
}
