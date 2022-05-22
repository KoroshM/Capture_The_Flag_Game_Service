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
    public Response getFlag(@QueryParam("session_id") int sid) {
        Random rand = new Random();
        int randNum = rand.nextInt(3); // pass in total number of flags in database, using 3 here
        String flagName = dao.getFlag(randNum);
        String feature1 = dao.getFeature1(randNum);
        Integer feature1_code = dao.getFeatureCode(feature1);
        String feature2 = dao.getFeature2(randNum);
        Integer feature2_code = dao.getFeatureCode(feature2);
        String feature3 = dao.getFeature3(randNum);
        Integer feature3_code = dao.getFeatureCode(feature3);

        try {
            dao.updateFlag(flagName, sid);
            dao.updateFeature1(feature1, sid);
            dao.updateFeature2(feature2, sid);
            dao.updateFeature3(feature3, sid);
            dao.updateFeature1Code(feature1_code, sid);
            dao.updateFeature2Code(feature2_code, sid);
            dao.updateFeature3Code(feature3_code, sid);

            return Response.ok(sid).build();
        } catch (Exception e) {
            return Response.ok(-1).build();
        }
    }
}
