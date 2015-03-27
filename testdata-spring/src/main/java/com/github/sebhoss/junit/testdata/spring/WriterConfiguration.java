/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata.spring;

import javax.inject.Inject;

import com.github.sebhoss.junit.testdata.Writer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;

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
        return new SpringJdbcBatchSqlWriter(jdbcTemplate);
    }

}
