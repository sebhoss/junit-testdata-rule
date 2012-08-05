package com.github.sebhoss.testdata.builder;

import java.lang.annotation.Annotation;

/**
 * 
 */
public final class TestDataRules {

    private TestDataRules() {
        // Utility class
    }

    public static <I extends Annotation, O extends Object> TestDataRuleBuilder<I, O> parse(final Class<I> annotation) {
        final TestDataRuleBuilder<I, O> builder = new TestDataRuleBuilder<>(annotation);

        return builder;
    }

    public static <I extends Annotation> TestDataReaderBuilder<I> reader() {
        return new TestDataReaderBuilder<>();
    }

    public static <O extends Object> TestDataEvaluatorBuilder<O> evaluator() {
        return new TestDataEvaluatorBuilder<>();
    }

}
