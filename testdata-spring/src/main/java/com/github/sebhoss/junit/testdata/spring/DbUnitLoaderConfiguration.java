/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.junit.testdata.spring;

import org.dbunit.util.fileloader.CsvDataFileLoader;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.dbunit.util.fileloader.FullXmlDataFileLoader;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
@SuppressWarnings("static-method")
public class DbUnitLoaderConfiguration {

    /**
     * @return A flat XML loader.
     */
    @Bean
    public DataFileLoader flatXmlLoader() {
        return new FlatXmlDataFileLoader();
    }

    /**
     * @return A full XML loader.
     */
    @Bean
    public DataFileLoader fullXmlLoader() {
        return new FullXmlDataFileLoader();
    }

    /**
     * @return A CSV loader.
     */
    @Bean
    public DataFileLoader csvLoader() {
        return new CsvDataFileLoader();
    }

    /**
     * @return An Excel (XLS) loader.
     */
    @Bean
    public DataFileLoader xlsLoader() {
        return new XlsDataFileLoader();
    }

}
