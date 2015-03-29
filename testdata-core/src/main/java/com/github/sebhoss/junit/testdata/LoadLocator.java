/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.runner.Description;

/**
 * Locator for the {@link Load} annotation.
 */
public final class LoadLocator implements TestDataLocator {

    @Override
    public boolean canAccess(final Description description) {
        final Load load = description.getAnnotation(Load.class);

        return load != null;
    }

    @Override
    public Stream<String> locateTestData(final Description description) {
        final Load load = description.getAnnotation(Load.class);

        return Arrays.stream(load.value());
    }

}
