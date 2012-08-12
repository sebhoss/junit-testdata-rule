package com.github.sebhoss.testdata.builder;

import java.lang.annotation.Annotation;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.sebhoss.testdata.Load;

/**
 * Tests for the {@link TestDataRules} factory.
 */
@SuppressWarnings({ "static-method", "nls" })
public class TestDataRulesTest {

    /** TestRule to catch exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test that checks for {@link NullPointerException}s when feeding a <code>null</code> annotation into the
     * parse-method.
     */
    @Test
    public void shouldThrowNPEForNullAnnotation() {
        // given
        this.thrown.expect(NullPointerException.class);

        // when
        TestDataRules.parse(null);

        // then
        Assert.fail("The factory should accept NULL as valid input!");
    }

    /**
     * Test that ensures that a test-data rule builder can be created with a valid annotation.
     */
    @Test
    public void shouldCreateRuleBuilderWithLoadAnnotation() {
        // given
        final Class<Load> annotation = Load.class;

        // when
        TestDataRuleBuilder<Load, String> builder = TestDataRules.parse(annotation);

        // then
        Assert.assertNotNull("The returned builder should never be NULL!", builder);
    }

    /**
     * Test that ensures that a test-data reader builder can be created.
     */
    @Test
    public void shouldCreateReaderBuilder() {
        // given
        // nothing

        // when
        TestDataReaderBuilder<Annotation> builder = TestDataRules.reader();

        // then
        Assert.assertNotNull("The returned builder should never be NULL!", builder);
    }

    /**
     * Test that ensures that a test-data evaluator builder can be created.
     */
    @Test
    public void shouldCreateEvaluatorBuilder() {
        // given
        // nothing

        // when
        TestDataEvaluatorBuilder<String> builder = TestDataRules.evaluator();

        // then
        Assert.assertNotNull("The returned builder should never be NULL!", builder);
    }

}
