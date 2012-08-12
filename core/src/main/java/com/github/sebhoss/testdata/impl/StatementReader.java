/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.testdata.impl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * Path-based reader for statements separated by a specific separator (e.g. ';' for SQL).
 */
public final class StatementReader {

    private final Charset charset;
    private final String  statementSeparator;

    /**
     * @param charset
     *            The charset to use.
     * @param statementSeparator
     *            The separator to use.
     */
    @Inject
    public StatementReader(final Charset charset, final String statementSeparator) {
        this.charset = charset;
        this.statementSeparator = statementSeparator;
    }

    /**
     * @param path
     *            The path to read.
     * @return A List of statements read from the given path.
     */
    public List<String> readStatementsFrom(final Path path) {
        try {
            final byte[] contentInBytes = Files.readAllBytes(path);
            final String contentAsString = new String(contentInBytes, this.charset);
            final String[] statements = contentAsString.split(this.statementSeparator);

            return Arrays.asList(statements);
        } catch (final IOException exception) {
            throw new IllegalStateException(exception);
        }
    }

}
