package com.github.sebhoss.testdata.spring;

import javax.inject.Inject;

import org.junit.runners.model.Statement;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import com.github.sebhoss.testdata.TestDataEvaluator;

public final class TransactionalTestDataEvaluator<O extends Object> implements TestDataEvaluator<O> {

    private final PlatformTransactionManager transactionManager;
    private final TestDataEvaluator<O>       evaluator;

    @Inject
    public TransactionalTestDataEvaluator(final PlatformTransactionManager transactionManager,
            final TestDataEvaluator<O> evaluator) {
        this.transactionManager = transactionManager;
        this.evaluator = evaluator;
    }

    @Override
    public void evaluateStatementWithData(final Statement statement, final Iterable<O> testdata) throws Throwable {
        final TransactionStatus transaction = this.transactionManager.getTransaction(null);

        this.evaluator.evaluateStatementWithData(statement, testdata);

        this.transactionManager.rollback(transaction);
    }

}
