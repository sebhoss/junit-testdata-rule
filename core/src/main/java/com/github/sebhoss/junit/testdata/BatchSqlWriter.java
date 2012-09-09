/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.junit.testdata;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.inject.Inject;
import javax.sql.DataSource;

/**
 * A test-data writer which writes SQL statements (Strings) into a datasource. All statements are batched.
 */
public final class BatchSqlWriter implements Writer<String> {

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
    public void writeTestData(final Iterable<String> data) {
        try (final Connection connection = this.dataSource.getConnection();
                final Statement statement = connection.createStatement()) {

            for (final String sql : data) {
                statement.addBatch(sql);
            }

            statement.executeBatch();

        } catch (final SQLException exception) {
            throw new IllegalStateException(exception);
        }
    }

}
