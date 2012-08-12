package com.github.sebhoss.testdata.builder;

import java.lang.annotation.Annotation;

import org.junit.rules.TestRule;

import com.github.sebhoss.testdata.Evaluator;
import com.github.sebhoss.testdata.Reader;
import com.github.sebhoss.testdata.impl.TestDataRule;
import com.google.common.base.Preconditions;

/**
 * 
 * @param <I>
 *            The annotation to parse.
 * @param <O>
 *            The type of the data to write.
 */
public final class TestDataRuleBuilder<I extends Annotation, O> {

    private final Class<I>       annotation;
    private Reader<I, O> reader;

    /**
     * @param annotation
     *            The annotation to scan.
     */
    public TestDataRuleBuilder(final Class<I> annotation) {
        this.annotation = Preconditions.checkNotNull(annotation);
    }

    /**
     * @param readerToUse
     *            The reader to use.
     * @return The current builder.
     */
    public TestDataRuleBuilder<I, O> with(final Reader<I, O> readerToUse) {
        this.reader = Preconditions.checkNotNull(readerToUse);

        return this;
    }

    /**
     * @param evaluator
     *            The evaluator to use.
     * @return A ready to use test-data rule.
     */
    public TestRule using(final Evaluator<O> evaluator) {
        Preconditions.checkNotNull(evaluator);

        Preconditions.checkState(this.reader != null);

        return new TestDataRule<>(this.annotation, this.reader, evaluator);
    }

}
