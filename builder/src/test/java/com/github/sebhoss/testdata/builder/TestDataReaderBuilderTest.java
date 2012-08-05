package com.github.sebhoss.testdata.builder;

import java.nio.charset.Charset;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import com.github.sebhoss.testdata.Load;
import com.github.sebhoss.testdata.TestDataLocator;
import com.github.sebhoss.testdata.TestDataReader;

@SuppressWarnings({ "static-method", "nls" })
public class TestDataReaderBuilderTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowNPEForNullLocator() {
        // given
        this.thrown.expect(NullPointerException.class);

        // when
        TestDataRules.<Load> reader().locatedBy(null);

        // then
        Assert.fail("The builder should never accept NULL as valid input!");
    }

    @Test
    public void shouldThrowNPEForNullSeparator() {
        // given
        this.thrown.expect(NullPointerException.class);

        // when
        TestDataRules.<Load> reader().separatedBy(null);

        // then
        Assert.fail("The builder should never accept NULL as valid input!");
    }

    @Test
    public void shouldThrowNPEForNullCharset() {
        // given
        this.thrown.expect(NullPointerException.class);

        // when
        TestDataRules.<Load> reader().encodedAs(null);

        // then
        Assert.fail("The builder should never accept NULL as valid input!");
    }

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

    @Test
    public void shouldThrowISEForMissingSeparator() {
        // given
        final TestDataLocator<Load> locator = Mockito.mock(TestDataLocator.class);
        final Charset charset = Mockito.mock(Charset.class);
        this.thrown.expect(IllegalStateException.class);

        // when
        TestDataRules.<Load> reader().locatedBy(locator).encodedAs(charset);

        // then
        Assert.fail("A reader should never leave the builder without a statement separator.");
    }

    @Test
    public void shouldCreateReader() {
        // given
        final TestDataLocator<Load> locator = Mockito.mock(TestDataLocator.class);
        final Charset charset = Mockito.mock(Charset.class);

        // when
        final TestDataReader<Load, String> reader = TestDataRules.<Load> reader().locatedBy(locator).separatedBy(";")
                .encodedAs(charset);

        // then
        Assert.assertNotNull("The builder must create a non-null object for valid input.", reader);
    }

}
