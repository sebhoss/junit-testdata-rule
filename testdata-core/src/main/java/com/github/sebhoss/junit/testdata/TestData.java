/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata;

import javax.inject.Inject;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Rule which optionally wraps a base JUnit {@link Statement} with a test-data driven statement.
 */
public final class TestData implements TestRule {

    private final Evaluator evaluator;

    /**
     * @param evaluator
     *            The evaluator to use.
     */
    @Inject
    public TestData(final Evaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public Statement apply(final Statement base, final Description description) {
        Statement statement = base;

        if (evaluator.hasTestDataFor(description)) {
            statement = new TestDataStatement(evaluator, base, description);
        }

        return statement;
    }

}
