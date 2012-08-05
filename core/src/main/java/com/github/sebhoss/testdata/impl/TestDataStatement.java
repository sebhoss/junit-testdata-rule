/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.testdata.impl;

import org.junit.runners.model.Statement;

import com.github.sebhoss.testdata.TestDataEvaluator;

/**
 * Special {@link Statement statement} which loads test data with the help of a {@link TestDataEvaluator}.
 * 
 * TestDataStatement instances are not meant to be injected but created at runtime. The supplied evaluator can be used
 * to implement e.g. transaction management.
 * 
 * @param <O>
 *            The type of the data to write.
 */
public final class TestDataStatement<O extends Object> extends Statement {

    private final TestDataEvaluator<O> evaluator;
    private final Statement            base;
    private final Iterable<O>          dataToLoad;

    public TestDataStatement(final TestDataEvaluator<O> evaluator, final Statement base, final Iterable<O> dataToLoad) {
        this.evaluator = evaluator;
        this.base = base;
        this.dataToLoad = dataToLoad;
    }

    /**
     * Delegates to the given evaluator.
     */
    @Override
    public void evaluate() throws Throwable {
        this.evaluator.evaluateStatementWithData(this.base, this.dataToLoad);
    }

}
