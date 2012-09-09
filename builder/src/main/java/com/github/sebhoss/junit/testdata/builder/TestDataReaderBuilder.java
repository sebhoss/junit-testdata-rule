package com.github.sebhoss.junit.testdata.builder;

import java.nio.charset.Charset;

import com.github.sebhoss.junit.testdata.PathBasedStatementReader;
import com.github.sebhoss.junit.testdata.Reader;
import com.github.sebhoss.junit.testdata.StatementReader;
import com.google.common.base.Preconditions;

/**
 * 
 */
public class TestDataReaderBuilder {

    private String statementSeparator;

    /**
     * @param separator
     *            The separator to use.
     * @return The current builder.
     */
    public TestDataReaderBuilder separatedBy(final String separator) {
        this.statementSeparator = Preconditions.checkNotNull(separator);

        return this;
    }

    /**
     * @param charset
     *            The charset to use.
     * @return A ready to use test-data reader.
     */
    public Reader<String> encodedAs(final Charset charset) {
        Preconditions.checkNotNull(charset);

        Preconditions.checkState(this.statementSeparator != null);

        final StatementReader reader = new StatementReader(charset, this.statementSeparator);

        return new PathBasedStatementReader(reader);
    }

}
