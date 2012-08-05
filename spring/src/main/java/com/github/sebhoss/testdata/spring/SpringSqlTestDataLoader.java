package com.github.sebhoss.testdata.spring;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcOperations;

import com.github.sebhoss.testdata.TestDataWriter;

public final class SpringSqlTestDataLoader implements TestDataWriter<String> {

    private final JdbcOperations database;

    @Inject
    public SpringSqlTestDataLoader(final JdbcOperations database) {
        this.database = database;
    }

    @Override
    public void writeTestData(final Iterable<String> testData) {
        final String[] batch = createBatch(testData);

        this.database.batchUpdate(batch);
    }

    private static String[] createBatch(final Iterable<String> testData) {
        final List<String> statements = new ArrayList<>();

        for (final String statement : testData) {
            statements.add(statement);
        }

        return statements.toArray(new String[statements.size()]);
    }

}
