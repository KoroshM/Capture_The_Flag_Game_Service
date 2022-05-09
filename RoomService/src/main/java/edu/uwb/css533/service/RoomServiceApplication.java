package edu.uwb.css533.service;

import edu.uwb.css533.service.db.RoomDao;
import edu.uwb.css533.service.resources.RoomResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class RoomServiceApplication extends Application<RoomServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new RoomServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "RoomService";
    }

    @Override
    public void initialize(final Bootstrap<RoomServiceConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final RoomServiceConfiguration configuration,
                    final Environment environment) {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final RoomDao room_dao = jdbi.onDemand(RoomDao.class);
        environment.jersey().register(new RoomResource(jdbi, room_dao));
    }

}
