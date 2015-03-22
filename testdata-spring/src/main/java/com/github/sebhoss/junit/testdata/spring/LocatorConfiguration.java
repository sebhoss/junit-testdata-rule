/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata.spring;

import com.github.sebhoss.junit.testdata.LoadLocator;
import com.github.sebhoss.junit.testdata.Locator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring-configuration for {@link Locator}s.
 */
@Configuration
public class LocatorConfiguration {

    /**
     * @return A locator for the Load annotation.
     */
    @Bean
    @SuppressWarnings("static-method")
    public Locator loadLocator() {
        return new LoadLocator();
    }

}
