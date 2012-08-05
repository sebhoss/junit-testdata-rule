/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.testdata.impl;

import javax.inject.Inject;

import org.junit.runners.model.Statement;

import com.github.sebhoss.testdata.TestDataEvaluator;
import com.github.sebhoss.testdata.TestDataWriter;
import com.github.sebhoss.testdata.TestDataExecutionPoint;

public final class LoadingTestDataEvaluator<O extends Object> implements TestDataEvaluator<O> {

    private final TestDataWriter<O> writer;
    private final TestDataExecutionPoint executionPoint;

    @Inject
    public LoadingTestDataEvaluator(final TestDataWriter<O> writer, final TestDataExecutionPoint executionPoint) {
        this.writer = writer;
        this.executionPoint = executionPoint;
    }

    @Override
    public void evaluateStatementWithData(final Statement statement, final Iterable<O> testdata) throws Throwable {
        if (TestDataExecutionPoint.BEFORE_STATEMENT == this.executionPoint) {
            this.writer.writeTestData(testdata);
        }

        statement.evaluate();

        if (TestDataExecutionPoint.AFTER_STATEMENT == this.executionPoint) {
            this.writer.writeTestData(testdata);
        }
    }

}
