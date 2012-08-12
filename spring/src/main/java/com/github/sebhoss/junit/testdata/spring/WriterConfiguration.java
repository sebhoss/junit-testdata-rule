/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.junit.testdata.spring;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;

import com.github.sebhoss.testdata.Writer;

/**
 * Spring-configuration for {@link Writer}s.
 */
@Configuration
public class WriterConfiguration {

    @Inject
    private JdbcOperations jdbcTemplate;

    /**
     * @return A Spring-based SQL writer.
     */
    @Bean
    public Writer<String> batchSqlWriter() {
        return new SpringBatchSqlWriter(this.jdbcTemplate);
    }

}
