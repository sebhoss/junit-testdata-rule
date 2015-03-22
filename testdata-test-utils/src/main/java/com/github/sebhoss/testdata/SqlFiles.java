/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.testdata;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Enumeration of SQL-files which contain ready-to-use test-data.
 */
public enum SqlFiles {

    /** Creates the test customer 'Hans' */
    CREATE_HANS("testdata/testdata.sql"); //$NON-NLS-1$

    /** The path to this SQL-file. */
    public final Path path;

    private SqlFiles(final String path) {
        this.path = Paths.get(path);
    }

}
