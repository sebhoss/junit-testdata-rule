/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.junit.testdata.guice;

import com.github.sebhoss.junit.testdata.Load;
import com.github.sebhoss.junit.testdata.TestData;
import com.google.inject.AbstractModule;

/**
 * Default Guice module for the {@link Load} annotation.
 */
public class DefaultLoadModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(TestData.class).asEagerSingleton();
    }

}
