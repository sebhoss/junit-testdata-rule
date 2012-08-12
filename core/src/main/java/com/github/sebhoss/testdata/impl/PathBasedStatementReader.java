/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.testdata.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.inject.Inject;

import com.github.sebhoss.testdata.Reader;
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
    public Iterable<String> readLocations(final Iterable<String> resourceLocations) {
        final List<String> allStatements = Lists.newArrayList();

        for (final String location : resourceLocations) {
            final Path pathToFile = Paths.get(location);
            final List<String> statement = this.reader.readStatementsFrom(pathToFile);

            allStatements.addAll(statement);
        }

        return allStatements;
    }

}
