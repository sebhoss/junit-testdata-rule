/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.sql.DataSource;

/**
 * A test-data writer which writes SQL statements (Strings) into a datasource. All statements are batched.
 */
public final class BatchSqlWriter implements TestDataWriter<String> {

    private final DataSource dataSource;

    /**
     * @param dataSource
     *            The DataSource to use.
     */
    @Inject
    public BatchSqlWriter(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void writeTestData(final Stream<String> data) {
        try (final Connection connection = dataSource.getConnection();
                final Statement statement = connection.createStatement()) {

            data.forEach(sql -> {
                try {
                    statement.addBatch(sql);
                } catch (final SQLException exception) {
                    throw new IllegalStateException(sql, exception);
                }
            });

            statement.executeBatch();

        } catch (final SQLException exception) {
            throw new IllegalStateException(exception);
        }
    }
}
