/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.testdata;

/**
 * @param <T>
 *            The type of the data to write.
 */
public interface Reader<T> {

    /**
     * @param resourceLocations
     *            The locations to look for resources.
     * @return The test data found at the given resource locations.
     */
    Iterable<T> readLocations(Iterable<String> resourceLocations);

}
