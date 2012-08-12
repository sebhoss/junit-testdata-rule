package com.github.sebhoss.testdata.builder;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.mockito.Mockito;

import com.github.sebhoss.testdata.Load;
import com.github.sebhoss.testdata.Evaluator;
import com.github.sebhoss.testdata.Reader;

/**
 * Tests for {@link TestDataRuleBuilder}s.
 */
@SuppressWarnings({ "nls", "static-method" })
public class TestDataRuleBuilderTest {

    /** TestRule to catch exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test that checks for {@link NullPointerException}s when feeding a <code>null</code> reader into a builder.
     */
    @Test
    public void shouldThrowNPEForNullReader() {
        // given
        this.thrown.expect(NullPointerException.class);

        // when
        TestDataRules.parse(Load.class).with(null);

        // then
        Assert.fail("The builder should accept NULL as valid input!");
    }

    /**
     * Test that checks for {@link NullPointerException}s when feeding a <code>null</code> evaluator into a builder.
     */
    @Test
    public void shouldThrowNPEForNullEvaluator() {
        // given
        final Reader<Load, String> reader = Mockito.mock(Reader.class);
        this.thrown.expect(NullPointerException.class);

        // when
        TestDataRules.<Load, String> parse(Load.class).with(reader).using(null);

        // then
        Assert.fail("The builder should accept NULL as valid input!");
    }

    /**
     * Test that checks for {@link IllegalStateException}s when creating a rule without a reader.
     */
    @Test
    public void shouldThrowISEForMissingReader() {
        // given
        final Evaluator<String> evaluator = Mockito.mock(Evaluator.class);
        this.thrown.expect(IllegalStateException.class);

        // when
        TestDataRules.<Load, String> parse(Load.class).using(evaluator);

        // then
        Assert.fail("The rule should never leave the builder without a reader.");
    }

    /**
     * Test that ensures that a TestRule can be built, given valid inputs.
     */
    @Test
    public void shouldCreateRuleWithValidInputs() {
        // given
        final Class<Load> annotation = Load.class;
        final Reader<Load, String> reader = Mockito.mock(Reader.class);
        final Evaluator<String> evaluator = Mockito.mock(Evaluator.class);

        // when
        final TestRule rule = TestDataRules.<Load, String> parse(annotation).with(reader).using(evaluator);

        // then
        Assert.assertNotNull("The returned rule should never be NULL!", rule);
    }

}
