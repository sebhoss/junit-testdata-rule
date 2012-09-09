/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.junit.testdata;

import org.junit.runner.Description;

/**
 * @param <T>
 *            The type of the test data.
 */
public interface Generator<T> {

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
    Iterable<T> generateTestData(Description description);

}
