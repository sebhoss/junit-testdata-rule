package com.github.sebhoss.testdata.builder;

import java.lang.annotation.Annotation;

import org.junit.rules.TestRule;

import com.github.sebhoss.testdata.TestDataEvaluator;
import com.github.sebhoss.testdata.TestDataReader;
import com.github.sebhoss.testdata.impl.TestDataRule;
import com.google.common.base.Preconditions;

public final class TestDataRuleBuilder<I extends Annotation, O extends Object> {

    private final Class<I>       annotation;
    private TestDataReader<I, O> reader;

    public TestDataRuleBuilder(final Class<I> annotation) {
        this.annotation = Preconditions.checkNotNull(annotation);
    }

    public TestDataRuleBuilder<I, O> with(final TestDataReader<I, O> readerToUse) {
        this.reader = Preconditions.checkNotNull(readerToUse);

        return this;
    }

    public TestRule using(final TestDataEvaluator<O> evaluator) {
        Preconditions.checkNotNull(evaluator);

        Preconditions.checkState(this.reader != null);

        return new TestDataRule<>(this.annotation, this.reader, evaluator);
    }

}
