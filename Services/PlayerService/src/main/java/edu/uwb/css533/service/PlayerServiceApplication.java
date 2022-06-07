package edu.uwb.css533.service;

import edu.uwb.css533.service.db.PlayerDao;
import edu.uwb.css533.service.resources.PlayerResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class PlayerServiceApplication extends Application<PlayerServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new PlayerServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "PlayerService";
    }

    @Override
    public void initialize(final Bootstrap<PlayerServiceConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final PlayerServiceConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final PlayerDao player_dao = jdbi.onDemand(PlayerDao.class);
        environment.jersey().register(new PlayerResource(jdbi, player_dao));
    }

}
