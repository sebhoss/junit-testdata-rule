package com.github.sebhoss.testdata.builder;

import java.nio.charset.Charset;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import com.github.sebhoss.testdata.Load;
import com.github.sebhoss.testdata.Locator;
import com.github.sebhoss.testdata.Reader;

/**
 * Test for {@link TestDataReaderBuilder}s.
 */
@SuppressWarnings({ "static-method", "nls" })
public class TestDataReaderBuilderTest {

    /** TestRule to catch exceptions. */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Test that checks for {@link NullPointerException}s when feeding a <code>null</code> locator into a builder.
     */
    @Test
    public void shouldThrowNPEForNullLocator() {
        // given
        this.thrown.expect(NullPointerException.class);

        // when
        TestDataRules.<Load> reader().locatedBy(null);

        // then
        Assert.fail("The builder should never accept NULL as valid input!");
    }

    /**
     * Test that checks for {@link NullPointerException}s when feeding a <code>null</code> separator into a builder.
     */
    @Test
    public void shouldThrowNPEForNullSeparator() {
        // given
        this.thrown.expect(NullPointerException.class);

        // when
        TestDataRules.<Load> reader().separatedBy(null);

        // then
        Assert.fail("The builder should never accept NULL as valid input!");
    }

    /**
     * Test that checks for {@link NullPointerException}s when feeding a <code>null</code> charset into a builder.
     */
    @Test
    public void shouldThrowNPEForNullCharset() {
        // given
        this.thrown.expect(NullPointerException.class);

        // when
        TestDataRules.<Load> reader().encodedAs(null);

        // then
        Assert.fail("The builder should never accept NULL as valid input!");
    }

    /**
     * Test that checks for {@link IllegalStateException}s when creating a reader without a locator.
     */
    @Test
    public void shouldThrowISEForMissingLocator() {
        // given
        final Charset charset = Mockito.mock(Charset.class);
        this.thrown.expect(IllegalStateException.class);

        // when
        TestDataRules.<Load> reader().separatedBy(";").encodedAs(charset);

        // then
        Assert.fail("A reader should never leave the builder without a locator.");
    }

    /**
     * Test that checks for {@link IllegalStateException}s when creating a reader without a separator.
     */
    @Test
    public void shouldThrowISEForMissingSeparator() {
        // given
        final Locator<Load> locator = Mockito.mock(Locator.class);
        final Charset charset = Mockito.mock(Charset.class);
        this.thrown.expect(IllegalStateException.class);

        // when
        TestDataRules.<Load> reader().locatedBy(locator).encodedAs(charset);

        // then
        Assert.fail("A reader should never leave the builder without a statement separator.");
    }

    /**
     * Test that ensures that a non-null reader can be built, given valid inputs.
     */
    @Test
    public void shouldCreateReaderWithValidInputs() {
        // given
        final Locator<Load> locator = Mockito.mock(Locator.class);
        final Charset charset = Mockito.mock(Charset.class);

        // when
        final Reader<Load, String> reader = TestDataRules.<Load> reader().locatedBy(locator).separatedBy(";")
                .encodedAs(charset);

        // then
        Assert.assertNotNull("The builder must create a non-null object for valid input.", reader);
    }

}
