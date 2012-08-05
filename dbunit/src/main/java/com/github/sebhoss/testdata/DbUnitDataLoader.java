/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.testdata;

import java.sql.SQLException;

import javax.inject.Inject;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;

import com.github.sebhoss.testdata.TestDataWriter;

/**
 * 
 * @see DatabaseOperation Possible database operations.
 */
public final class DbUnitDataLoader implements TestDataWriter<IDataSet> {

    private final IDatabaseConnection connection;
    private final DatabaseOperation   operation;

    @Inject
    public DbUnitDataLoader(final IDatabaseConnection connection, final DatabaseOperation operation) {
        this.connection = connection;
        this.operation = operation;
    }

    @Override
    public void writeTestData(final Iterable<IDataSet> testData) {
        try {

            for (final IDataSet dataSet : testData) {
                this.operation.execute(this.connection, dataSet);
            }

        } catch (final DatabaseUnitException | SQLException exception) {
            throw new IllegalStateException(exception);
        }
    }

}
