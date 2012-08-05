package com.github.sebhoss.testdata.builder;

import java.lang.annotation.Annotation;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.sebhoss.testdata.Load;

@SuppressWarnings({ "static-method", "nls" })
public class TestDataRulesTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowNPEForNullAnnotation() {
        // given
        this.thrown.expect(NullPointerException.class);

        // when
        TestDataRules.parse(null);

        // then
        Assert.fail("The factory should accept NULL as valid input!");
    }

    @Test
    public void shouldCreateRuleBuilderWithLoadAnnotation() {
        // given
        final Class<Load> annotation = Load.class;

        // when
        TestDataRuleBuilder<Load, String> builder = TestDataRules.parse(annotation);

        // then
        Assert.assertNotNull("The returned builder should never be NULL!", builder);
    }

    @Test
    public void shouldCreateReaderBuilder() {
        // given
        // nothing

        // when
        TestDataReaderBuilder<Annotation> builder = TestDataRules.reader();

        // then
        Assert.assertNotNull("The returned builder should never be NULL!", builder);
    }

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
