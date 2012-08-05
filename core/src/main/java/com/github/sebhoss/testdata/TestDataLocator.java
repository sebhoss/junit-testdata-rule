/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.testdata;

import java.lang.annotation.Annotation;

/**
 * Locates files which contain test data from a specific {@link Annotation annotation}.
 * 
 * @param <I>
 *            The annotation to parse.
 */
public interface TestDataLocator<I extends Annotation> {

    /**
     * Locates the specified files to load from an annotation.
     * 
     * @param annotation
     *            The annotation to parse.
     * @return The locations of the specified files.
     */
    Iterable<String> locateFilesToLoad(I annotation);

}
