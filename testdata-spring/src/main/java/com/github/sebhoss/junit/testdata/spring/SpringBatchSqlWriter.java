/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata.spring;

import java.util.List;

import javax.inject.Inject;

import com.github.sebhoss.junit.testdata.Writer;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import org.springframework.jdbc.core.JdbcOperations;

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

        database.batchUpdate(batch);
    }

    private static String[] createBatch(final Iterable<String> testData) {
        final List<String> statements = Lists.newArrayList();

        for (final String statement : testData) {
            statements.add(statement);
        }

        return Iterables.toArray(statements, String.class);
    }

}
