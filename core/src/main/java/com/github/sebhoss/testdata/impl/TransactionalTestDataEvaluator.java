package com.github.sebhoss.testdata.impl;

import javax.inject.Inject;
import javax.transaction.TransactionManager;

import org.junit.runners.model.Statement;

import com.github.sebhoss.testdata.TestDataEvaluator;

public final class TransactionalTestDataEvaluator<O extends Object> implements TestDataEvaluator<O> {

    private final TransactionManager   transactionManager;
    private final TestDataEvaluator<O> evaluator;

    @Inject
    public TransactionalTestDataEvaluator(final TransactionManager transactionManager,
            final TestDataEvaluator<O> evaluator) {
        this.transactionManager = transactionManager;
        this.evaluator = evaluator;
    }

    @Override
    public void evaluateStatementWithData(final Statement statement, final Iterable<O> testdata) throws Throwable {
        this.transactionManager.begin();

        this.evaluator.evaluateStatementWithData(statement, testdata);

        this.transactionManager.rollback();
    }

}
