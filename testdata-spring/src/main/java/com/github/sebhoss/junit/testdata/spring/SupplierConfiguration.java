/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.junit.testdata.spring;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.github.sebhoss.junit.testdata.ReadingSupplier;
import com.github.sebhoss.junit.testdata.Supplier;

/**
 * Spring-configuration for {@link Supplier}s.
 */
@Configuration
@Import({ LocatorConfiguration.class, ReaderConfiguration.class })
public class SupplierConfiguration {

    @Inject
    private LocatorConfiguration locators;

    @Inject
    private ReaderConfiguration  readers;

    /**
     * @return A reading supplier.
     */
    @Bean
    public Supplier<String> loadSqlSupplier() {
        return new ReadingSupplier<>(this.locators.loadLocator(), this.readers.sqlReader());
    }

}
