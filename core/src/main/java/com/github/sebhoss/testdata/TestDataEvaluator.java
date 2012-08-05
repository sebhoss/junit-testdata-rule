/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.testdata;

import org.junit.runners.model.Statement;

/**
 * Evaluates a {@link Statement statement} with a given set of test data.
 * 
 * @param <O>
 *            The type of the data to write.
 */
public interface TestDataEvaluator<O extends Object> {

    /**
     * Evaluates the given statement with the given test data.
     * 
     * @param statement
     *            The statement to evaluate.
     * @param testdata
     *            The test data to use.
     * @throws Throwable
     *             For whatever reason.
     */
    void evaluateStatementWithData(Statement statement, Iterable<O> testdata) throws Throwable;

}
