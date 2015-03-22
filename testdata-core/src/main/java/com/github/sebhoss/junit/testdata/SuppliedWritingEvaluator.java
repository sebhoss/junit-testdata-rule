/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata;

import javax.inject.Inject;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * @param <T>
 *            The type of the data to write.
 */
public final class SuppliedWritingEvaluator<T> implements Evaluator {

    private final Supplier<T>    supplier;
    private final Writer<T>      writer;
    private final ExecutionPoint executionPoint;

    /**
     * @param supplier
     *            The supplier to use.
     * @param writer
     *            The writer to use.
     * @param executionPoint
     *            The point of execution.
     */
    @Inject
    public SuppliedWritingEvaluator(final Supplier<T> supplier, final Writer<T> writer,
            final ExecutionPoint executionPoint) {
        this.supplier = supplier;
        this.writer = writer;
        this.executionPoint = executionPoint;
    }

    @Override
    public boolean hasTestDataFor(final Description description) {
        return this.supplier.hasTestDataFor(description);
    }

    @Override
    public void evaluateStatementWithTestData(final Statement statement, final Description description)
            throws Throwable {
        final Iterable<T> testData = this.supplier.getTestData(description);

        if (ExecutionPoint.BEFORE_STATEMENT == this.executionPoint) {
            this.writer.writeTestData(testData);
        }

        statement.evaluate();

        if (ExecutionPoint.AFTER_STATEMENT == this.executionPoint) {
            this.writer.writeTestData(testData);
        }
    }

}
