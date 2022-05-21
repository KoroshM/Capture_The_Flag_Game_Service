package edu.uwb.css533.service.resources;

import edu.uwb.css533.service.db.FlagDao;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Random;

@Path("/flag")
public class FlagResource {

    private Jdbi jdbi;
    private FlagDao dao;
    private int session_id;

    public FlagResource(Jdbi jdbi, FlagDao dao) {
        this.jdbi = jdbi;
        this.dao = dao;
    }

    @GET
    @Path("/get_flag")
    public Response getFlag(@QueryParam("session_id") int id) {
        Random rand = new Random();
        int randNum = rand.nextInt(2); // pass in total number of flags in database, using 10 here
        String flagName = dao.getFlag(randNum);
        String feature1 = dao.getFeature1(randNum);
        String feature2 = dao.getFeature1(randNum);
        String feature3 = dao.getFeature1(randNum);
        dao.updateFlag(flagName, id);
        dao.updateFeature1(feature1, id);
        dao.updateFeature1(feature2, id);
        dao.updateFeature1(feature3, id);
        return Response.ok(flagName).build();
    }

    @GET
    @Path("/get_feature_codes")
    public Response startGame(@QueryParam("flag_name") int name) {
        return Response.ok("not done").build();
    }
}
