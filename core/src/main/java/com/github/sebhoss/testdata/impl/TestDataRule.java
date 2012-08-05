/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.testdata.impl;

import java.lang.annotation.Annotation;

import javax.inject.Inject;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import com.github.sebhoss.testdata.TestDataEvaluator;
import com.github.sebhoss.testdata.TestDataReader;

/**
 * {@link TestRule} which loads data for a {@link Statement statement}.
 * 
 * The data is retrieved from an {@link Annotation annotation} which is applied to a statement.
 * 
 * @param <I>
 *            The annotation to parse.
 * @param <O>
 *            The type of the data to write.
 */
public final class TestDataRule<I extends Annotation, O extends Object> implements TestRule {

    private final Class<I>             annotationToScan;
    private final TestDataReader<I, O> reader;
    private final TestDataEvaluator<O> evaluator;

    /**
     * @param annotationToScan
     *            The annotation which holds information about the data to load.
     * @param reader
     *            The reader is used to retrieve data from an annotation.
     * @param evaluator
     *            The evaluator is used to evaluate a statement together with the previously retrieved data.
     */
    @Inject
    public TestDataRule(final Class<I> annotationToScan, final TestDataReader<I, O> reader,
            final TestDataEvaluator<O> evaluator) {
        this.annotationToScan = annotationToScan;
        this.reader = reader;
        this.evaluator = evaluator;
    }

    /**
     * Retrieves the given annotation, reads all test data from it and returns a {@link TestDataStatement} which will
     * load the given data when it is evaluated.
     */
    @Override
    public Statement apply(final Statement base, final Description description) {
        if (this.needsTestData(description)) {
            final Iterable<O> dataToLoad = this.readTestData(description);

            return new TestDataStatement<>(this.evaluator, base, dataToLoad);
        }

        return base;
    }

    private boolean needsTestData(final Description description) {
        return this.retrieveAnnotation(description) != null;
    }

    private Iterable<O> readTestData(final Description description) {
        final I annotationToLoad = this.retrieveAnnotation(description);

        return this.reader.readFromAnnotation(annotationToLoad);
    }

    private I retrieveAnnotation(final Description description) {
        return description.getAnnotation(this.annotationToScan);
    }

}
