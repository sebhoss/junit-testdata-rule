/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata.dbunit;

import java.sql.SQLException;
import java.util.stream.Stream;

import javax.inject.Inject;

import com.github.sebhoss.junit.testdata.TestDataWriter;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;

/**
 * DbUnit-based writer.
 *
 * @see DatabaseOperation Possible database operations.
 */
public final class DbUnitWriter implements TestDataWriter<IDataSet> {

    private final IDatabaseConnection connection;
    private final DatabaseOperation   operation;

    /**
     * @param connection
     *            The database connection to use.
     * @param operation
     *            The database operation to perform.
     */
    @Inject
    public DbUnitWriter(final IDatabaseConnection connection,
            final DatabaseOperation operation) {
        this.connection = connection;
        this.operation = operation;
    }

    @Override
    public void writeTestData(final Stream<IDataSet> testData) {
        testData.forEach(data -> {
            try {
                operation.execute(connection, data);
            } catch (DatabaseUnitException | SQLException exception) {
                throw new IllegalStateException(data.toString(), exception);
            }
        });
    }

}
