/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata.spring;

import java.util.Arrays;

import com.github.sebhoss.junit.testdata.DelegatingLocator;
import com.github.sebhoss.junit.testdata.LoadLocator;
import com.github.sebhoss.junit.testdata.LoadingLocator;
import com.github.sebhoss.junit.testdata.Locator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring-configuration for {@link Locator}s.
 */
@Configuration
public class LocatorConfiguration {

    /**
     * @return A locator for the delegating locator. Currently delegates its call to all the other available locators in
     *         this class. Therefore its wise to inject this locator instead of the other onces.
     */
    @Bean
    @ConditionalOnMissingBean(Locator.class)
    public Locator delegatingLocator() {
        return new DelegatingLocator(Arrays.asList(loadLocator(), loadingLocator()));
    }

    @SuppressWarnings("static-method")
    private Locator loadLocator() {
        return new LoadLocator();
    }

    @SuppressWarnings("static-method")
    private Locator loadingLocator() {
        return new LoadingLocator();
    }

}
