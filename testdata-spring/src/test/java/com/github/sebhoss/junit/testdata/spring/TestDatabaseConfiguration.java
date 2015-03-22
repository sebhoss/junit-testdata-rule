/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata.spring;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Database configuration for test classes. Uses a in-memory H2 database and supplies a standard {@link JdbcTemplate}.
 */
@Configuration
public class TestDatabaseConfiguration {

    /**
     * @return A transaction manager.
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    /**
     * @return JDBC access.
     */
    @Bean
    public JdbcOperations jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    /**
     * @return A datasource.
     */
    @Bean
    @SuppressWarnings("static-method")
    public DataSource dataSource() {
        final EmbeddedDatabase database = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:/schema/defaultSchema.sql").build(); //$NON-NLS-1$
        return database;
    }
}
