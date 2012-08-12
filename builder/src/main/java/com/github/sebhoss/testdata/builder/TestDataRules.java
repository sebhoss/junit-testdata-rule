package com.github.sebhoss.testdata.builder;

import java.lang.annotation.Annotation;

/**
 * Factory for test-data rules and companion objects.
 */
public final class TestDataRules {

    private TestDataRules() {
        // Utility class
    }

    /**
     * @param annotation
     *            The annotation to scan.
     * @return A builder for test-data rules.
     */
    public static <I extends Annotation, O extends Object> TestDataRuleBuilder<I, O> parse(final Class<I> annotation) {
        final TestDataRuleBuilder<I, O> builder = new TestDataRuleBuilder<>(annotation);

        return builder;
    }

    /**
     * @return A new test-data reader.
     */
    public static <I extends Annotation> TestDataReaderBuilder<I> reader() {
        return new TestDataReaderBuilder<>();
    }

    /**
     * @return A new test-data evaluator.
     */
    public static <O extends Object> TestDataEvaluatorBuilder<O> evaluator() {
        return new TestDataEvaluatorBuilder<>();
    }

}
