package com.github.sebhoss.testdata.builder;

import java.lang.annotation.Annotation;
import java.nio.charset.Charset;

import com.github.sebhoss.testdata.TestDataLocator;
import com.github.sebhoss.testdata.TestDataReader;
import com.github.sebhoss.testdata.impl.PathBasedTestDataReader;
import com.google.common.base.Preconditions;

public class TestDataReaderBuilder<I extends Annotation> {

    private TestDataLocator<I> locator;
    private String             statementSeparator;

    public TestDataReaderBuilder<I> locatedBy(final TestDataLocator<I> locatorToUse) {
        this.locator = Preconditions.checkNotNull(locatorToUse);

        return this;
    }

    public TestDataReaderBuilder<I> separatedBy(final String separator) {
        this.statementSeparator = Preconditions.checkNotNull(separator);

        return this;
    }

    public TestDataReader<I, String> encodedAs(final Charset charset) {
        Preconditions.checkNotNull(charset);

        Preconditions.checkState(this.locator != null);
        Preconditions.checkState(this.statementSeparator != null);

        return new PathBasedTestDataReader<>(this.locator, this.statementSeparator, charset);
    }

}
