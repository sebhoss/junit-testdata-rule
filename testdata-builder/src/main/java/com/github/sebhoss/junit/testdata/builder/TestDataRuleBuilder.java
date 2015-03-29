/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata.builder;

import org.junit.rules.TestRule;

import com.github.sebhoss.junit.testdata.Evaluator;
import com.github.sebhoss.junit.testdata.TestDataReader;
import com.github.sebhoss.junit.testdata.TestData;
import com.google.common.base.Preconditions;

/**
 * 
 * @param <T>
 *            The type of the data to write.
 */
public final class TestDataRuleBuilder<T> {

    private TestDataReader<T> reader;

    /**
     * @param readerToUse
     *            The reader to use.
     * @return The current builder.
     */
    public TestDataRuleBuilder<T> with(final TestDataReader<T> readerToUse) {
        this.reader = Preconditions.checkNotNull(readerToUse);

        return this;
    }

    /**
     * @param evaluator
     *            The evaluator to use.
     * @return A ready to use test-data rule.
     */
    public TestRule using(final Evaluator evaluator) {
        Preconditions.checkNotNull(evaluator);

        Preconditions.checkState(this.reader != null);

        return new TestData(evaluator);
    }

}
