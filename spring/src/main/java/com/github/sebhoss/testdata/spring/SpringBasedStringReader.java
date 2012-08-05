package com.github.sebhoss.testdata.spring;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.github.sebhoss.testdata.TestDataLocator;
import com.github.sebhoss.testdata.TestDataReader;

public final class SpringBasedStringReader<I extends Annotation> implements TestDataReader<I, String> {

    private final TestDataLocator<I> locator;
    private final ResourceLoader     loader;
    private final Charset            charset;
    private final String             statementSeparator;

    @Inject
    public SpringBasedStringReader(final TestDataLocator<I> locator, final ResourceLoader loader,
            final Charset charset, final String statementSeparator) {
        this.locator = locator;
        this.loader = loader;
        this.charset = charset;
        this.statementSeparator = statementSeparator;
    }

    @Override
    public Iterable<String> readFromAnnotation(final I annotation) {
        final Iterable<String> locations = this.locator.locateFilesToLoad(annotation);
        final List<String> allStatements = new ArrayList<>();

        for (final String location : locations) {
            final Resource resource = this.loader.getResource(location);
            final Collection<String> statements = this.loadStatements(resource);

            allStatements.addAll(statements);
        }

        return allStatements;
    }

    private Collection<String> loadStatements(final Resource resource) {
        try {
            final byte[] contentInBytes = Files.readAllBytes(Paths.get(resource.getURI()));
            final String contentAsString = new String(contentInBytes, this.charset);
            final String[] statements = contentAsString.split(this.statementSeparator);

            return Arrays.asList(statements);
        } catch (final IOException exception) {
            throw new IllegalStateException(exception);
        }
    }

}
