/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata;

import java.util.List;

import javax.inject.Inject;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Delegates calls to a list of given evaluators. All matching evaluators will be called.
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
        return evaluators.stream().anyMatch(evaluator -> evaluator.hasTestDataFor(description));
    }

    @Override
    public void evaluateStatementWithTestData(final Statement statement, final Description description)
            throws Throwable {
        // can't use streams because we need to throw something
        for (final Evaluator evaluator : evaluators) {
            if (evaluator.hasTestDataFor(description)) {
                evaluator.evaluateStatementWithTestData(statement, description);
            }
        }
    }

}
