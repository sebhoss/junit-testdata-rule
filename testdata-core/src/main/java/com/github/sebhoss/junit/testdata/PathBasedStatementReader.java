/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata;

import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.inject.Inject;

/**
 * Test-data reader based on the Java 7 NIO API using a {@link StatementReader}.
 */
public final class PathBasedStatementReader implements TestDataReader<String> {

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
    public Stream<String> readLocations(final Stream<String> locations) {
        return locations.map(Paths::get).flatMap(reader::readStatementsFrom);
    }

}
