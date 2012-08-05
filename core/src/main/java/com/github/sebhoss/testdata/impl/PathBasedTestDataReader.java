/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.testdata.impl;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.inject.Inject;

import com.github.sebhoss.testdata.TestDataLocator;
import com.github.sebhoss.testdata.TestDataReader;

public final class PathBasedTestDataReader<I extends Annotation> implements TestDataReader<I, String> {

    private final TestDataLocator<I> locator;
    private final String             statementSeparator;
    private final Charset            charset;

    @Inject
    public PathBasedTestDataReader(final TestDataLocator<I> locator, final String statementSeparator,
            final Charset charset) {
        this.locator = locator;
        this.statementSeparator = statementSeparator;
        this.charset = charset;
    }

    @Override
    public Iterable<String> readFromAnnotation(final I annotation) {
        final Iterable<String> locations = this.locator.locateFilesToLoad(annotation);
        final StringBuilder statements = new StringBuilder();

        for (final String location : locations) {
            final Path pathToFile = Paths.get(location);
            final String content = readContent(pathToFile);

            statements.append(content);
        }

        final String[] astatements = statements.toString().split(this.statementSeparator);

        return Arrays.asList(astatements);
    }

    private String readContent(final Path pathToSqlFile) {
        final byte[] contentInBytes = readContents(pathToSqlFile);
        final String contentAsString = new String(contentInBytes, this.charset);

        return contentAsString;
    }

    private static byte[] readContents(final Path pathToSqlFile) {
        try {
            byte[] contentInBytes = Files.readAllBytes(pathToSqlFile);

            return contentInBytes;
        } catch (final IOException exception) {
            throw new IllegalStateException(exception);
        }
    }

}
