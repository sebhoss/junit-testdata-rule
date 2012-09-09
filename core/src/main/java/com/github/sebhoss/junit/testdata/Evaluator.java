/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.junit.testdata;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Evaluates a {@link Statement statement} with a given set of test data.
 */
public interface Evaluator {

    /**
     * @param description
     *            The description of a JUnit test statement.
     * @return <code>true</code> if this factory has test data for the given test, <code>false</code> otherwise.
     */
    boolean hasTestDataFor(Description description);

    /**
     * Evaluates the given statement with the given test data.
     * 
     * @param statement
     *            The statement to evaluate.
     * @param description
     *            The description of the statement to evaluate.
     * @throws Throwable
     *             For whatever reason.
     */
    void evaluateStatementWithTestData(Statement statement, Description description) throws Throwable;

}
