/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.testdata.impl;

import java.util.Arrays;

import org.junit.runner.Description;

import com.github.sebhoss.testdata.Load;
import com.github.sebhoss.testdata.Locator;

/**
 * Locator for the {@link Load} annotation.
 */
public final class LoadLocator implements Locator {

    @Override
    public boolean canAccess(final Description description) {
        final Load load = description.getAnnotation(Load.class);

        return load != null;
    }

    @Override
    public Iterable<String> locateFilesToLoad(final Description description) {
        final Load load = description.getAnnotation(Load.class);
        final String[] locations = load.value();

        return Arrays.asList(locations);
    }

}
