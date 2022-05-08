package edu.uwb.css533.service;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
    }

}
