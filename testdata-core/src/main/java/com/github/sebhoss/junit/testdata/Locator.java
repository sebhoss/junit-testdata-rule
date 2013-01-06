/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.junit.testdata;

import org.junit.runner.Description;

/**
 * Locates files which contain test data for a specific test method.
 */
public interface Locator {

    /**
     * @param description
     *            The description to check.
     * @return <code>true</code> if this locator can access the specified locations, <code>false</code> otherwise.
     */
    boolean canAccess(Description description);

    /**
     * Locates the specified files to load from an statement description.
     * 
     * @param description
     *            The description to parse.
     * @return The locations of the specified files.
     */
    Iterable<String> locateFilesToLoad(Description description);

}
