/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata.spring;

import javax.inject.Inject;

import com.github.sebhoss.junit.testdata.Evaluator;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

/**
 * Transactional test-data evaluator using Springs {@link PlatformTransactionManager}.
 */
public final class SpringTransactionalEvaluator implements Evaluator {

    private final PlatformTransactionManager transactionManager;
    private final Evaluator                  evaluator;

    /**
     * @param transactionManager
     *            The transaction manager to use.
     * @param evaluator
     *            The evaluator to use.
     */
    @Inject
    public SpringTransactionalEvaluator(
            final PlatformTransactionManager transactionManager,
            final Evaluator evaluator) {
        this.transactionManager = transactionManager;
        this.evaluator = evaluator;
    }

    @Override
    public boolean hasTestDataFor(final Description description) {
        return evaluator.hasTestDataFor(description);
    }

    @Override
    public void evaluateStatementWithTestData(final Statement statement,
            final Description description) throws Throwable {
        final TransactionStatus transaction = transactionManager.getTransaction(null); // defaults

        evaluator.evaluateStatementWithTestData(statement, description);

        transactionManager.rollback(transaction);
    }

}
