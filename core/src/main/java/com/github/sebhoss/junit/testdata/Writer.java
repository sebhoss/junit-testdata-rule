/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.junit.testdata;

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
    void writeTestData(Iterable<T> testData);

}
