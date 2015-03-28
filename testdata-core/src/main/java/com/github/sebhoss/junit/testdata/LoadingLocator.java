/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata;

import static java.util.Arrays.stream;

import java.util.Arrays;
import java.util.stream.Stream;

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
    public Stream<String> locateTestData(final Description description) {
        final Loading loading = description.getAnnotation(Loading.class);

        return retrieveLocations(loading);
    }

    private static Stream<String> retrieveLocations(final Loading loading) {
        return stream(loading.value())
                .map(Load::value)
                .flatMap(Arrays::stream);
    }

}
