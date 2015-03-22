/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata.spring;

import javax.inject.Inject;

import com.github.sebhoss.junit.testdata.ReadingSupplier;
import com.github.sebhoss.junit.testdata.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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
        return new ReadingSupplier<>(locators.loadLocator(),
                readers.sqlReader());
    }

}