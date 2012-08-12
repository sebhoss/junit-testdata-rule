/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.junit.testdata.spring;

import javax.inject.Inject;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import com.github.sebhoss.testdata.Evaluator;

/**
 * Transactional test-data evaluator.
 */
public final class TransactionalEvaluator implements Evaluator {

    private final PlatformTransactionManager transactionManager;
    private final Evaluator                  evaluator;

    /**
     * @param transactionManager
     *            The transaction manager to use.
     * @param evaluator
     *            The evaluator to use.
     */
    @Inject
    public TransactionalEvaluator(final PlatformTransactionManager transactionManager, final Evaluator evaluator) {
        this.transactionManager = transactionManager;
        this.evaluator = evaluator;
    }

    @Override
    public boolean hasTestDataFor(final Description description) {
        return this.evaluator.hasTestDataFor(description);
    }

    @Override
    public void evaluateStatementWithTestData(final Statement statement, final Description description)
            throws Throwable {
        final TransactionStatus transaction = this.transactionManager.getTransaction(null);

        this.evaluator.evaluateStatementWithTestData(statement, description);

        this.transactionManager.rollback(transaction);
    }

}
