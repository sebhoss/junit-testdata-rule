/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.junit.testdata.spring;

import javax.inject.Inject;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.github.sebhoss.junit.testdata.Reader;
import com.github.sebhoss.junit.testdata.Writer;
import com.github.sebhoss.junit.testdata.dbunit.DbUnitReader;
import com.github.sebhoss.junit.testdata.dbunit.DbUnitWriter;

/**
 * Spring configuration for DBUnit classes.
 */
@Configuration
@Import(DbUnitLoaderConfiguration.class)
public class DbUnitConfiguration {

    @Inject
    private DbUnitLoaderConfiguration loader;

    @Inject
    private IDatabaseConnection       connection;

    /**
     * @return A DbUnit-based reader for flat XML files.
     */
    @Bean
    public Reader<IDataSet> flatXmlReader() {
        return new DbUnitReader(this.loader.flatXmlLoader());
    }

    /**
     * @return A DbUnit-based writer which {@link DatabaseOperation#REFRESH refreshes} the database.
     */
    @Bean
    public Writer<IDataSet> refreshingWriter() {
        return new DbUnitWriter(this.connection, DatabaseOperation.REFRESH);
    }

}
