/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.testdata.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.inject.Inject;
import javax.sql.DataSource;

import com.github.sebhoss.testdata.TestDataWriter;

public final class SqlTestDataLoader implements TestDataWriter<String> {

    private final DataSource dataSource;

    @Inject
    public SqlTestDataLoader(final DataSource dataSource) {
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
