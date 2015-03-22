/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.junit.testdata.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import com.github.sebhoss.junit.testdata.Reader;
import com.github.sebhoss.junit.testdata.StatementReader;
import com.google.common.base.Charsets;

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
        return new SpringStatementReader(this.resourceLoader(), this.statementReader());
    }

    /**
     * @return A Spring resource loader.
     */
    @Bean
    public ResourceLoader resourceLoader() {
        return new DefaultResourceLoader();
    }

    /**
     * @return A statement reader.
     */
    @Bean
    public StatementReader statementReader() {
        return new StatementReader(Charsets.UTF_8, ";");
    }

}
