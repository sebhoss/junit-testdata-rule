package com.github.sebhoss.junit.testdata.dbunit;

import java.util.List;

import javax.inject.Inject;

import org.dbunit.dataset.IDataSet;
import org.dbunit.util.fileloader.DataFileLoader;

import com.github.sebhoss.junit.testdata.Reader;
import com.google.common.collect.Lists;

/**
 * A reader for DbUnit data.
 */
public final class DbUnitReader implements Reader<IDataSet> {

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
    public Iterable<IDataSet> readLocations(final Iterable<String> resourceLocations) {
        final List<IDataSet> dataSets = Lists.newArrayList();

        for (final String location : resourceLocations) {
            final IDataSet ds = this.loader.load(location);

            dataSets.add(ds);
        }

        return dataSets;
    }

}
