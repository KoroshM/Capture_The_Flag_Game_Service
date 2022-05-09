package edu.uwb.css533.service;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        // TODO: implement application
    }

}
