/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link BatchSqlWriter}
 */
public class BatchSqlWriterTest {

    private static final String       FIRST_DATA  = "A";                                               //$NON-NLS-1$
    private static final String       SECOND_DATA = "B";                                               //$NON-NLS-1$
    private static final String       THIRD_DATA  = "C";                                               //$NON-NLS-1$
    private static final List<String> TEST_DATA   = Arrays.asList(FIRST_DATA, SECOND_DATA, THIRD_DATA);

    /**
     * verifies thrown exceptions during code execution
     */
    @Rule
    public ExpectedException          thrown      = ExpectedException.none();

    private DataSource                dataSource;
    private Connection                connection;
    private Statement                 statement;
    private BatchSqlWriter            writer;

    /**
     * creates a mock data source
     *
     * @throws SQLException
     *             In case something really really strange happens
     */
    @Before
    public void setUp() throws SQLException {
        statement = mock(Statement.class);

        connection = mock(Connection.class);
        given(connection.createStatement()).willReturn(statement);

        dataSource = mock(DataSource.class);
        given(dataSource.getConnection()).willReturn(connection);

        writer = new BatchSqlWriter(dataSource);
    }

    /**
     * Ensures that the writer batches the supplied test data into one statement
     *
     * @throws SQLException
     *             In case something really really strange happens
     */
    @Test
    public void shouldBatchTestData() throws SQLException {
        // given

        // when
        writer.writeTestData(TEST_DATA.stream());

        // then
        then(dataSource).should(only()).getConnection();
        then(connection).should().createStatement();
        then(statement).should().addBatch(FIRST_DATA);
        then(statement).should().addBatch(SECOND_DATA);
        then(statement).should().addBatch(THIRD_DATA);
    }

    /**
     * Ensures that the writer wraps an SQLException into an IllegalStateException in cause it can't get a connection.
     *
     * @throws SQLException
     *             In case something really really strange happens
     */
    @Test
    public void shouldWrapSqlExceptionInIllegalStateExceptionWhileGettingConnection() throws SQLException {
        // given
        given(dataSource.getConnection()).willThrow(new SQLException());

        // when
        thrown.expect(IllegalStateException.class);

        // then
        writer.writeTestData(TEST_DATA.stream());
    }

    /**
     * Ensures that the writer wraps an SQLException into an IllegalStateException in cause it can't create a statement.
     *
     * @throws SQLException
     *             In case something really really strange happens
     */
    @Test
    public void shouldWrapSqlExceptionInIllegalStateExceptionWhileCreatingStatement() throws SQLException {
        // given
        given(connection.createStatement()).willThrow(new SQLException());

        // when
        thrown.expect(IllegalStateException.class);

        // then
        writer.writeTestData(TEST_DATA.stream());
    }

    /**
     * Ensures that the writer wraps an SQLException into an IllegalStateException in cause it can't add data to the
     * batch.
     *
     * @throws SQLException
     *             In case something really really strange happens
     */
    @Test
    public void shouldWrapSqlExceptionInIllegalStateExceptionWhileAddingDataToBatch() throws SQLException {
        // given
        doThrow(new SQLException()).when(statement).addBatch(FIRST_DATA);

        // when
        thrown.expect(IllegalStateException.class);

        // then
        writer.writeTestData(TEST_DATA.stream());
    }

    /**
     * Ensures that the writer wraps an SQLException into an IllegalStateException in cause it can't execute the
     * statement.
     *
     * @throws SQLException
     *             In case something really really strange happens
     */
    @Test
    public void shouldWrapSqlExceptionInIllegalStateExceptionWhileExecutingStatement() throws SQLException {
        // given
        doThrow(new SQLException()).when(statement).executeBatch();

        // when
        thrown.expect(IllegalStateException.class);

        // then
        writer.writeTestData(TEST_DATA.stream());
    }

}
