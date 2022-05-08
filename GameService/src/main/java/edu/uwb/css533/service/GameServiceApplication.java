package edu.uwb.css533.service;

import edu.uwb.css533.service.resources.PlayerResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class GameServiceApplication extends Application<GameServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new GameServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "GameService";
    }

    @Override
    public void initialize(final Bootstrap<GameServiceConfiguration> bootstrap) {
        // TODO: application initialization
        // nothing to do yet
    }

    @Override
    public void run(final GameServiceConfiguration configuration,
                    final Environment environment) {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        //final GameDao game_dao = jdbi.onDemand(GameDao.class);
        environment.jersey().register(new PlayerResource());
        //environment.jersey().register(new GameResource(jdbi, game_dao));
    }

}
