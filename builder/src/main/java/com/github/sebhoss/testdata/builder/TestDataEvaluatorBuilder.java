package com.github.sebhoss.testdata.builder;

import javax.transaction.TransactionManager;

import com.github.sebhoss.testdata.TestDataEvaluator;
import com.github.sebhoss.testdata.TestDataWriter;
import com.github.sebhoss.testdata.TestDataExecutionPoint;
import com.github.sebhoss.testdata.impl.LoadingTestDataEvaluator;
import com.github.sebhoss.testdata.impl.TransactionalTestDataEvaluator;
import com.google.common.base.Preconditions;

public final class TestDataEvaluatorBuilder<O extends Object> {

    private TestDataWriter<O>  loader;
    private TransactionManager transactionManager;

    public TestDataEvaluatorBuilder<O> loadedBy(final TestDataWriter<O> loaderToUse) {
        this.loader = Preconditions.checkNotNull(loaderToUse);

        return this;
    }

    public TestDataEvaluatorBuilder<O> managedBy(final TransactionManager transactionManagerToUse) {
        this.transactionManager = transactionManagerToUse;

        return this;
    }

    public TestDataEvaluator<O> at(final TestDataExecutionPoint loadpoint) {
        Preconditions.checkNotNull(loadpoint);

        Preconditions.checkState(this.loader != null);

        final TestDataEvaluator<O> evaluator = new LoadingTestDataEvaluator<>(this.loader, loadpoint);

        if (this.transactionManager == null) {
            return evaluator;
        }

        Preconditions.checkState(this.transactionManager != null);

        return new TransactionalTestDataEvaluator<>(this.transactionManager, evaluator);
    }

}
