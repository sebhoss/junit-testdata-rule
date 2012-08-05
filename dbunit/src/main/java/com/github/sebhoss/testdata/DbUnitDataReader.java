package com.github.sebhoss.testdata;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.dbunit.dataset.IDataSet;
import org.dbunit.util.fileloader.DataFileLoader;

import com.github.sebhoss.testdata.TestDataLocator;
import com.github.sebhoss.testdata.TestDataReader;

public final class DbUnitDataReader<I extends Annotation> implements TestDataReader<I, IDataSet> {

    private final TestDataLocator<I> locator;
    private final DataFileLoader     loader;

    @Inject
    public DbUnitDataReader(final TestDataLocator<I> locator, final DataFileLoader loader) {
        this.locator = locator;
        this.loader = loader;
    }

    @Override
    public Iterable<IDataSet> readFromAnnotation(final I annotation) {
        final Iterable<String> locations = this.locator.locateFilesToLoad(annotation);
        final List<IDataSet> dataSets = new ArrayList<>();

        for (final String location : locations) {
            final IDataSet ds = this.loader.load(location);

            dataSets.add(ds);
        }

        return dataSets;
    }

}
