package com.github.sebhoss.testdata.builder;

import javax.transaction.TransactionManager;

import com.github.sebhoss.testdata.Evaluator;
import com.github.sebhoss.testdata.ExecutionPoint;
import com.github.sebhoss.testdata.Writer;
import com.github.sebhoss.testdata.impl.TransactionalEvaluator;
import com.github.sebhoss.testdata.impl.TestDataEvaluator;
import com.google.common.base.Preconditions;

/**
 * 
 * @param <T>
 *            The type of the data to write.
 */
public final class TestDataEvaluatorBuilder<T> {

    private Writer<T>  writer;
    private TransactionManager transactionManager;

    /**
     * @param writerToUse
     *            The writer to use.
     * @return The current builder.
     */
    public TestDataEvaluatorBuilder<T> loadedBy(final Writer<T> writerToUse) {
        this.writer = Preconditions.checkNotNull(writerToUse);

        return this;
    }

    /**
     * @param transactionManagerToUse
     *            The transaction manager to use.
     * @return The current builder.
     */
    public TestDataEvaluatorBuilder<T> managedBy(final TransactionManager transactionManagerToUse) {
        this.transactionManager = transactionManagerToUse;

        return this;
    }

    /**
     * @param executionPoint
     *            The execution point to use.
     * @return A ready to use test-data evaluator.
     */
    public Evaluator<T> at(final ExecutionPoint executionPoint) {
        Preconditions.checkNotNull(executionPoint);

        Preconditions.checkState(this.writer != null);

        final Evaluator<T> evaluator = new TestDataEvaluator<>(this.writer, executionPoint);

        if (this.transactionManager == null) {
            return evaluator;
        }

        Preconditions.checkState(this.transactionManager != null);

        return new TransactionalEvaluator<>(this.transactionManager, evaluator);
    }

}
