/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.testdata;

import java.lang.annotation.Annotation;

/**
 * Reads test data from an {@link Annotation annotation}.
 * 
 * @param <I>
 *            The annotation to parse.
 * @param <O>
 *            The type of the data to write.
 */
public interface TestDataReader<I extends Annotation, O extends Object> {

    /**
     * Reads test data specified by an annotation.
     * 
     * @param load
     *            The annotation to read.
     * @return The test data specified by the given annotation.
     */
    Iterable<O> readFromAnnotation(I load);

}
