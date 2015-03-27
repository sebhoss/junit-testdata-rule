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

import org.springframework.jdbc.core.JdbcOperations;

/**
 * Spring-based test-data writer.
 */
public final class SpringJdbcBatchSqlWriter implements Writer<String> {

    private final JdbcOperations database;

    /**
     * @param database
     *            The database wrapper to use.
     */
    @Inject
    public SpringJdbcBatchSqlWriter(final JdbcOperations database) {
        this.database = database;
    }

    @Override
    public void writeTestData(final List<String> testData) {
        database.batchUpdate(testData.toArray(new String[testData.size()]));
    }

}
