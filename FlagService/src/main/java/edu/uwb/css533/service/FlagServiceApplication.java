package edu.uwb.css533.service;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class FlagServiceApplication extends Application<FlagServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new FlagServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "FlagService";
    }

    @Override
    public void initialize(final Bootstrap<FlagServiceConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final FlagServiceConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
