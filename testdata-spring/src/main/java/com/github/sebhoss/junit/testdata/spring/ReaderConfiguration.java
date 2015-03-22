/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata.spring;

import com.github.sebhoss.junit.testdata.Reader;
import com.github.sebhoss.junit.testdata.StatementReader;
import com.google.common.base.Charsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

/**
 * Spring-configuration for {@link Reader}s.
 */
@Configuration
public class ReaderConfiguration {

    /**
     * @return A Spring-based reader for statements.
     */
    @Bean
    public Reader<String> sqlReader() {
        return new SpringStatementReader(resourceLoader(), statementReader());
    }

    /**
     * @return A Spring resource loader.
     */
    @Bean
    @SuppressWarnings("static-method")
    public ResourceLoader resourceLoader() {
        return new DefaultResourceLoader();
    }

    /**
     * @return A statement reader.
     */
    @Bean
    @SuppressWarnings("static-method")
    public StatementReader statementReader() {
        return new StatementReader(Charsets.UTF_8, ";"); //$NON-NLS-1$
    }

}
