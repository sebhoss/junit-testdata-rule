/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.inject.Inject;

import com.google.common.collect.Lists;

/**
 * Test-data reader based on the Java 7 NIO API using a {@link StatementReader}.
 */
public final class PathBasedStatementReader implements Reader<String> {

    private final StatementReader reader;

    /**
     * @param reader
     *            The reader to use.
     */
    @Inject
    public PathBasedStatementReader(final StatementReader reader) {
        this.reader = reader;
    }

    @Override
    public List<String> readLocations(final List<String> resourceLocations) {
        final List<String> allStatements = Lists.newArrayList();

        for (final String location : resourceLocations) {
            final Path pathToFile = Paths.get(location);
            final List<String> statement = reader.readStatementsFrom(pathToFile);

            allStatements.addAll(statement);
        }

        return allStatements;
    }

}
