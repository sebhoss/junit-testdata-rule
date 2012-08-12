package com.github.sebhoss.junit.testdata.spring;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.inject.Inject;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.github.sebhoss.testdata.Reader;
import com.github.sebhoss.testdata.impl.StatementReader;
import com.google.common.collect.Lists;

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
    public Iterable<String> readLocations(final Iterable<String> resourceLocations) {
        final List<String> allStatements = Lists.newArrayList();

        for (final String location : resourceLocations) {
            final Resource resource = this.loader.getResource(location);
            final List<String> statements = this.reader.readStatementsFrom(SpringStatementReader.createPath(resource));

            allStatements.addAll(statements);
        }

        return allStatements;
    }

    private static Path createPath(final Resource resource) {
        try {
            return Paths.get(resource.getURI());
        } catch (final IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
