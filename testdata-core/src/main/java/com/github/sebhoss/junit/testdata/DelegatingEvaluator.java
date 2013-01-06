/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.junit.testdata;

import java.util.List;

import javax.inject.Inject;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Delegates calls to a list of given evaluators. Only the first matching evaluator will be called.
 */
public class DelegatingEvaluator implements Evaluator {

    private final List<Evaluator> evaluators;

    /**
     * @param evaluators
     *            The evaluators to use.
     */
    @Inject
    public DelegatingEvaluator(final List<Evaluator> evaluators) {
        this.evaluators = evaluators;
    }

    @Override
    public boolean hasTestDataFor(final Description description) {
        boolean testDataAvailable = false;

        for (final Evaluator evaluator : this.evaluators) {
            testDataAvailable |= evaluator.hasTestDataFor(description);

            if (testDataAvailable) {
                break;
            }
        }

        return testDataAvailable;
    }

    @Override
    public void evaluateStatementWithTestData(final Statement statement, final Description description)
            throws Throwable {
        for (final Evaluator evaluator : this.evaluators) {
            if (evaluator.hasTestDataFor(description)) {
                evaluator.evaluateStatementWithTestData(statement, description);
                break;
            }
        }
    }

}
