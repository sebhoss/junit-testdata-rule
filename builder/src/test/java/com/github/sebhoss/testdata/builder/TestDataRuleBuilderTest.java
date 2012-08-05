package com.github.sebhoss.testdata.builder;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.mockito.Mockito;

import com.github.sebhoss.testdata.Load;
import com.github.sebhoss.testdata.TestDataEvaluator;
import com.github.sebhoss.testdata.TestDataReader;

@SuppressWarnings({ "nls", "static-method" })
public class TestDataRuleBuilderTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowNPEForNullReader() {
        // given
        this.thrown.expect(NullPointerException.class);

        // when
        TestDataRules.parse(Load.class).with(null);

        // then
        Assert.fail("The builder should accept NULL as valid input!");
    }

    @Test
    public void shouldThrowNPEForNullEvaluator() {
        // given
        final TestDataReader<Load, String> reader = Mockito.mock(TestDataReader.class);
        this.thrown.expect(NullPointerException.class);

        // when
        TestDataRules.<Load, String> parse(Load.class).with(reader).using(null);

        // then
        Assert.fail("The builder should accept NULL as valid input!");
    }

    @Test
    public void shouldThrowISEForMissingReader() {
        // given
        final TestDataEvaluator<String> evaluator = Mockito.mock(TestDataEvaluator.class);
        this.thrown.expect(IllegalStateException.class);

        // when
        TestDataRules.<Load, String> parse(Load.class).using(evaluator);

        // then
        Assert.fail("The rule should never leave the builder without a reader.");
    }

    @Test
    public void shouldCreateRule() {
        // given
        final Class<Load> annotation = Load.class;
        final TestDataReader<Load, String> reader = Mockito.mock(TestDataReader.class);
        final TestDataEvaluator<String> evaluator = Mockito.mock(TestDataEvaluator.class);

        // when
        final TestRule rule = TestDataRules.<Load, String> parse(annotation).with(reader).using(evaluator);

        // then
        Assert.assertNotNull("The returned rule should never be NULL!", rule);
    }

}
