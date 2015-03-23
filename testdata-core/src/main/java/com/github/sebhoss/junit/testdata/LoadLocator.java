/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.runner.Description;

/**
 * Locator for the {@link Load} annotation.
 */
public final class LoadLocator implements Locator {

    @Override
    public boolean canAccess(final Description description) {
        final Load load = description.getAnnotation(Load.class);
        final Loading loading = description.getAnnotation(Loading.class);

        return load != null || loading != null;
    }

    @Override
    public List<String> locateFilesToLoad(final Description description) {
        final Load load = description.getAnnotation(Load.class);
        final Loading loading = description.getAnnotation(Loading.class);

        return retrieveLocations(load, loading);
    }

    private static List<String> retrieveLocations(final Load load, final Loading loading) {
        if (load != null) {
            return Arrays.asList(load.value());
        }

        return Arrays.stream(loading.value())
                .map(loadData -> loadData.value())
                .map(locations -> Arrays.stream(locations).collect(Collectors.toList()))
                .collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
    }

}
