/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata;

import java.util.stream.Stream;

/**
 * Writes test data.
 *
 * @param <T>
 *            The type of the data to write.
 */
public interface Writer<T> {

    /**
     * Writes the given test data.
     *
     * @param testData
     *            The data to write.
     */
    void writeTestData(Stream<T> testData);

}
