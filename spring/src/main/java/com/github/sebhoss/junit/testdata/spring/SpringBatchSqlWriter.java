package com.github.sebhoss.junit.testdata.spring;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcOperations;

import com.github.sebhoss.testdata.Writer;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Spring-based test-data writer.
 */
public final class SpringBatchSqlWriter implements Writer<String> {

    private final JdbcOperations database;

    /**
     * @param database
     *            The database wrapper to use.
     */
    @Inject
    public SpringBatchSqlWriter(final JdbcOperations database) {
        this.database = database;
    }

    @Override
    public void writeTestData(final Iterable<String> testData) {
        final String[] batch = SpringBatchSqlWriter.createBatch(testData);

        this.database.batchUpdate(batch);
    }

    private static String[] createBatch(final Iterable<String> testData) {
        final List<String> statements = Lists.newArrayList();

        for (final String statement : testData) {
            statements.add(statement);
        }

        return Iterables.toArray(statements, String.class);
    }

}
