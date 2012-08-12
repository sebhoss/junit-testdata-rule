/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.testdata.impl;

import javax.inject.Inject;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import com.github.sebhoss.testdata.Evaluator;

/**
 * Rule which optionally wraps a base JUnit {@link Statement} with a test-data driven statement.
 */
public final class TestDataRule implements TestRule {

    private final Evaluator evaluator;

    /**
     * @param evaluator
     *            The evaluator to use.
     */
    @Inject
    public TestDataRule(final Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public Statement apply(final Statement base, final Description description) {
        Statement statement = base;

        if (this.evaluator.hasTestDataFor(description)) {
            statement = new TestDataStatement(this.evaluator, base, description);
        }

        return statement;
    }

}
