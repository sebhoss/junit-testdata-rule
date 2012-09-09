/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.junit.testdata.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.sebhoss.junit.testdata.LoadLocator;
import com.github.sebhoss.junit.testdata.Locator;

/**
 * Spring-configuration for {@link Locator}s.
 */
@Configuration
@SuppressWarnings("static-method")
public class LocatorConfiguration {

    /**
     * @return A locator for the Load annotation.
     */
    @Bean
    public Locator loadLocator() {
        return new LoadLocator();
    }

}
