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
public class GeneratingSupplier<T> implements Supplier<T> {

    private final Generator<T> generator;

    /**
     * @param generator
     *            The generator to use.
     */
    @Inject
    public GeneratingSupplier(final Generator<T> generator) {
        this.generator = generator;
    }

    @Override
    public boolean hasTestDataFor(final Description description) {
        return this.generator.canGenerate(description);
    }

    @Override
    public Iterable<T> getTestData(final Description description) {
        final Iterable<T> testData = this.generator.generateTestData(description);

        return testData;
    }

}
