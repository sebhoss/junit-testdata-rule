/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata;

import java.util.stream.Stream;

import org.junit.runner.Description;

/**
 * @param <T>
 *            The type of the test data.
 */
public interface TestDataGenerator<T> {

    /**
     * @param description
     *            The description of a JUnit test statement.
     * @return <code>true</code> if this generator can generate test data for the given test, <code>false</code>
     *         otherwise.
     */
    boolean canGenerate(Description description);

    /**
     * @param description
     *            The description of a JUnit test statement.
     * @return The generated test data
     */
    Stream<T> generateTestData(Description description);

}
