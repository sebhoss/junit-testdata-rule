/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata.dbunit;

import java.util.stream.Stream;

import javax.inject.Inject;

import com.github.sebhoss.junit.testdata.TestDataReader;

import org.dbunit.dataset.IDataSet;
import org.dbunit.util.fileloader.DataFileLoader;

/**
 * A reader for DbUnit data.
 */
public final class DbUnitReader implements TestDataReader<IDataSet> {

    private final DataFileLoader loader;

    /**
     * @param loader
     *            The loader to use.
     */
    @Inject
    public DbUnitReader(final DataFileLoader loader) {
        this.loader = loader;
    }

    @Override
    public Stream<IDataSet> readLocations(final Stream<String> locations) {
        return locations.map(loader::load);
    }

}
