package com.search;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class SearchApplication extends Application<SearchConfig> {
    public static void main(String[] args) throws Exception {
        new SearchApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void run(SearchConfig configuration, Environment environment) throws Exception {
        final SearchResource res = new SearchResource();
        environment.jersey().register(res);
    }
}
