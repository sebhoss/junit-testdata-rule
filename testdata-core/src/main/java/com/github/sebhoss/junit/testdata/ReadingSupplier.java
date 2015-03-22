/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata;

import java.util.List;

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
        final List<String> resourceLocations = this.locator.locateFilesToLoad(description);
        final List<T> testData = this.reader.readLocations(resourceLocations);

        return testData;
    }

}