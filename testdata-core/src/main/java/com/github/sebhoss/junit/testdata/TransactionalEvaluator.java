/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata;

import javax.inject.Inject;
import javax.transaction.TransactionManager;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Evaluator which wraps another evaluator into a single transaction.
 */
public final class TransactionalEvaluator implements Evaluator {

    private final TransactionManager transactionManager;
    private final Evaluator          evaluator;

    /**
     * @param transactionManager
     *            The transaction manager to use.
     * @param evaluator
     *            The test-data evaluator to use.
     */
    @Inject
    public TransactionalEvaluator(final TransactionManager transactionManager, final Evaluator evaluator) {
        this.transactionManager = transactionManager;
        this.evaluator = evaluator;
    }

    @Override
    public boolean hasTestDataFor(final Description description) {
        return evaluator.hasTestDataFor(description);
    }

    @Override
    public void evaluateStatementWithTestData(final Statement statement, final Description description)
            throws Throwable {
        transactionManager.begin();

        evaluator.evaluateStatementWithTestData(statement, description);

        transactionManager.rollback();
    }

}
