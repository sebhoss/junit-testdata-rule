package com.github.sebhoss.testdata;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Enumeration of SQL-files which contain ready-to-use test-data.
 */
@SuppressWarnings("nls")
public enum SqlFiles {

    /** Creates the test customer 'Hans' */
    CREATE_HANS("src/main/resources/testdata/testdata.sql");

    /** The path to this SQL-file. */
    public final Path path;

    private SqlFiles(final String path) {
        this.path = Paths.get(path);
    }

}
