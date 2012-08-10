/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.junit.testdata.guice;

import com.github.sebhoss.testdata.Load;
import com.github.sebhoss.testdata.impl.TestDataRule;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

/**
 * Default Guice module for the {@link Load} annotation.
 */
public class DefaultLoadTestDataModule extends AbstractModule {

    @Override
    protected void configure() {
        this.bind(new TypeLiteral<TestDataRule<Load, String>>() {
            /* Type-Literal */
        }).asEagerSingleton();
    }

}
