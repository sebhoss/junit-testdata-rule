/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata;

import java.util.stream.Stream;

import javax.inject.Inject;

import org.junit.runner.Description;

/**
 * @param <T>
 *            The type of the test data.
 */
public class GeneratingSupplier<T> implements TestDataSupplier<T> {

    private final TestDataGenerator<T> generator;

    /**
     * @param generator
     *            The generator to use.
     */
    @Inject
    public GeneratingSupplier(final TestDataGenerator<T> generator) {
        this.generator = generator;
    }

    @Override
    public boolean hasTestDataFor(final Description description) {
        return generator.canGenerate(description);
    }

    @Override
    public Stream<T> getTestData(final Description description) {
        return generator.generateTestData(description);
    }

}
