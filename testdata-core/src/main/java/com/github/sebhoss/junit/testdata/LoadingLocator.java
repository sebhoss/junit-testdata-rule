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
 * Locator for the {@link Loading} annotation.
 */
public final class LoadingLocator implements Locator {

    @Override
    public boolean canAccess(final Description description) {
        final Loading loading = description.getAnnotation(Loading.class);

        return loading != null;
    }

    @Override
    public List<String> locateFilesToLoad(final Description description) {
        final Loading loading = description.getAnnotation(Loading.class);

        return retrieveLocations(loading);
    }

    private static List<String> retrieveLocations(final Loading loading) {
        return Arrays.stream(loading.value())
                .map(loadData -> loadData.value())
                .map(locations -> Arrays.stream(locations).collect(Collectors.toList()))
                .collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
    }

}
