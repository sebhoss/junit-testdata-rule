package com.github.sebhoss.testdata;

import java.nio.file.Path;
import java.nio.file.Paths;

@SuppressWarnings("nls")
public enum SqlFiles {

    CREATE_HANS("src/main/resources/testdata/testdata.sql");

    public final Path path;

    private SqlFiles(final String path) {
        this.path = Paths.get(path);
    }

}
