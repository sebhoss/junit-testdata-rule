/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata.builder;

import javax.transaction.TransactionManager;

import com.github.sebhoss.junit.testdata.Evaluator;
import com.github.sebhoss.junit.testdata.ExecutionPoint;
import com.github.sebhoss.junit.testdata.SuppliedWritingEvaluator;
import com.github.sebhoss.junit.testdata.Supplier;
import com.github.sebhoss.junit.testdata.TransactionalEvaluator;
import com.github.sebhoss.junit.testdata.Writer;
import com.google.common.base.Preconditions;

/**
 * 
 * @param <T>
 *            The type of the data to write.
 */
public final class EvaluatorBuilder<T> {

    private Supplier<T>        supplier;
    private Writer<T>          writer;
    private TransactionManager transactionManager;

    /**
     * @param supplierToUse
     *            The supplier to use.
     * @return The current builder.
     */
    public EvaluatorBuilder<T> suppliedBy(final Supplier<T> supplierToUse) {
        this.supplier = Preconditions.checkNotNull(supplierToUse);

        return this;
    }

    /**
     * @param writerToUse
     *            The writer to use.
     * @return The current builder.
     */
    public EvaluatorBuilder<T> loadedBy(final Writer<T> writerToUse) {
        this.writer = Preconditions.checkNotNull(writerToUse);

        return this;
    }

    /**
     * @param transactionManagerToUse
     *            The transaction manager to use.
     * @return The current builder.
     */
    public EvaluatorBuilder<T> managedBy(final TransactionManager transactionManagerToUse) {
        this.transactionManager = transactionManagerToUse;

        return this;
    }

    /**
     * @param executionPoint
     *            The execution point to use.
     * @return A ready to use test-data evaluator.
     */
    public Evaluator at(final ExecutionPoint executionPoint) {
        Preconditions.checkNotNull(executionPoint);

        Preconditions.checkState(this.writer != null);

        final SuppliedWritingEvaluator<T> evaluator = new SuppliedWritingEvaluator<>(this.supplier, this.writer,
                executionPoint);

        if (this.transactionManager == null) {
            return evaluator;
        }

        Preconditions.checkState(this.transactionManager != null);

        return new TransactionalEvaluator(this.transactionManager, evaluator);
    }

}
