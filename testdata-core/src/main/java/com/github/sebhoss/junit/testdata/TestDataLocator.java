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
 * Locates files which contain test data for a specific test method.
 */
public interface TestDataLocator {

    /**
     * @param description
     *            The description to check.
     * @return <code>true</code> if this locator can access the specified locations, <code>false</code> otherwise.
     */
    boolean canAccess(Description description);

    /**
     * Locates the test data to load from a statement description.
     *
     * @param description
     *            The description to parse.
     * @return The locations of the specified files.
     */
    Stream<String> locateTestData(Description description);

}
