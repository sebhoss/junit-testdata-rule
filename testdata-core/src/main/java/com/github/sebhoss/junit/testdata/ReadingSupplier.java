/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.junit.testdata;

import javax.inject.Inject;

import org.junit.runner.Description;

/**
 * @param <T>
 *            The type of the test data.
 */
public class ReadingSupplier<T> implements Supplier<T> {

    private final Locator   locator;
    private final Reader<T> reader;

    /**
     * @param locator
     *            The locator to use.
     * @param reader
     *            The reader to use.
     */
    @Inject
    public ReadingSupplier(final Locator locator, final Reader<T> reader) {
        this.locator = locator;
        this.reader = reader;
    }

    @Override
    public boolean hasTestDataFor(final Description description) {
        return this.locator.canAccess(description);
    }

    @Override
    public Iterable<T> getTestData(final Description description) {
        final Iterable<String> resourceLocations = this.locator.locateFilesToLoad(description);
        final Iterable<T> testData = this.reader.readLocations(resourceLocations);

        return testData;
    }

}
