package com.github.sebhoss.testdata.builder;

import java.lang.annotation.Annotation;
import java.nio.charset.Charset;

import com.github.sebhoss.testdata.Locator;
import com.github.sebhoss.testdata.Reader;
import com.github.sebhoss.testdata.impl.PathBasedStatementReader;
import com.github.sebhoss.testdata.impl.StatementReader;
import com.google.common.base.Preconditions;

/**
 * 
 * @param <I>
 *            The annotation to parse.
 */
public class TestDataReaderBuilder<I extends Annotation> {

    private Locator<I> locator;
    private String             statementSeparator;

    /**
     * @param locatorToUse
     *            The locator to use.
     * @return The current builder.
     */
    public TestDataReaderBuilder<I> locatedBy(final Locator<I> locatorToUse) {
        this.locator = Preconditions.checkNotNull(locatorToUse);

        return this;
    }

    /**
     * @param separator
     *            The separator to use.
     * @return The current builder.
     */
    public TestDataReaderBuilder<I> separatedBy(final String separator) {
        this.statementSeparator = Preconditions.checkNotNull(separator);

        return this;
    }

    /**
     * @param charset
     *            The charset to use.
     * @return A ready to use test-data reader.
     */
    public Reader<I, String> encodedAs(final Charset charset) {
        Preconditions.checkNotNull(charset);

        Preconditions.checkState(this.locator != null);
        Preconditions.checkState(this.statementSeparator != null);

        final StatementReader reader = new StatementReader(charset, this.statementSeparator);

        return new PathBasedStatementReader<>(this.locator, reader);
    }

}
