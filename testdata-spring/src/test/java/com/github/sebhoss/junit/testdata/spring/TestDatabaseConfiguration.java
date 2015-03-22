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
        return new DataSourceTransactionManager(this.dataSource());
    }

    /**
     * @return JDBC access.
     */
    @Bean
    public JdbcOperations jdbcTemplate() {
        return new JdbcTemplate(this.dataSource());
    }

    /**
     * @return A datasource.
     */
    @Bean
    public DataSource dataSource() {
        final EmbeddedDatabase database = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:/schema/defaultSchema.sql").build();
        return database;
    }
}
