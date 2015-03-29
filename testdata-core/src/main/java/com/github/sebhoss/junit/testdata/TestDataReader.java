/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata;

import java.util.stream.Stream;

/**
 * @param <T>
 *            The type of the data to write.
 */
public interface TestDataReader<T> {

    /**
     * @param locations
     *            The locations of the resources to read.
     * @return The test data found at the given resource locations.
     */
    Stream<T> readLocations(Stream<String> locations);

}
