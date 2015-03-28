/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata.spring;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.inject.Inject;

import com.github.sebhoss.junit.testdata.Reader;
import com.github.sebhoss.junit.testdata.StatementReader;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * Spring-based test-data reader.
 */
public final class SpringStatementReader implements Reader<String> {

    private final ResourceLoader  loader;
    private final StatementReader reader;

    /**
     * @param loader
     *            The loader to use.
     * @param reader
     *            The reader to use.
     */
    @Inject
    public SpringStatementReader(final ResourceLoader loader, final StatementReader reader) {
        this.loader = loader;
        this.reader = reader;
    }

    @Override
    public Stream<String> readLocations(final Stream<String> locations) {
        return locations
                .map(loader::getResource)
                .map(SpringStatementReader::createPath)
                .flatMap(reader::readStatementsFrom);
    }

    private static Path createPath(final Resource resource) {
        try {
            return Paths.get(resource.getURI());
        } catch (final IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
