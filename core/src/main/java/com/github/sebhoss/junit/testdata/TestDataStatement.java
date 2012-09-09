/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.junit.testdata;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Special {@link Statement statement} which loads test data with the help of a {@link Evaluator}.
 * 
 * TestDataStatement instances are not meant to be injected but created at runtime. The supplied evaluator can be used
 * to implement custom logic, e.g. transaction management.
 */
public final class TestDataStatement extends Statement {

    private final Evaluator   evaluator;
    private final Statement   base;
    private final Description description;

    /**
     * @param evaluator
     *            The evaluator to use.
     * @param base
     *            The wrapped statement.
     * @param description
     *            The description of the wrapped statement.
     */
    public TestDataStatement(final Evaluator evaluator, final Statement base, final Description description) {
        this.evaluator = evaluator;
        this.base = base;
        this.description = description;
    }

    /**
     * Delegates to the given evaluator.
     */
    @Override
    public void evaluate() throws Throwable {
        this.evaluator.evaluateStatementWithTestData(this.base, this.description);
    }

}
