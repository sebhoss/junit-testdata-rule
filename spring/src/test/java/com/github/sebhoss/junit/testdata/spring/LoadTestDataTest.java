package com.github.sebhoss.junit.testdata.spring;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.sebhoss.junit.testdata.spring.DefaultLoadSqlConfiguration;
import com.github.sebhoss.testdata.Load;

/**
 * Test for the default Spring configuration.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DefaultLoadSqlConfiguration.class, TestDatabaseConfiguration.class })
@SuppressWarnings("nls")
public final class LoadTestDataTest {

    private static final String CUSTOMER_COUNT = "SELECT COUNT(*) FROM customers";

    /** A test-data rule which inspects tests annotated with @Load. */
    @Inject
    @Rule
    public TestRule             testdata;

    /** JDBC access to a database. */
    @Inject
    private JdbcOperations      jdbcOperations;

    /**
     * Test ensures that the test-data rule reads the annotated test data and writes it into a database.
     */
    @Test
    @Load("classpath:/testdata/new customers.sql")
    public void shouldCountTwoCustomers() {
        // given

        // when
        final int numberOfCustomers = this.jdbcOperations.queryForInt(CUSTOMER_COUNT);

        // then
        Assert.assertEquals(2, numberOfCustomers);
    }

    /**
     * Test ensures that test data is loaded in isolation.
     */
    @Test
    public void shouldCountNoCustomers() {
        // given

        // when
        final int numberOfCustomers = this.jdbcOperations.queryForInt(CUSTOMER_COUNT);

        // then
        Assert.assertEquals(0, numberOfCustomers);
    }

    /**
     * Test ensures that test data can be loaded multiple times.
     */
    @Test
    @Load("classpath:/testdata/new customers.sql")
    public void shouldCountTwoCustomersAgain() {
        // given

        // when
        final int numberOfCustomers = this.jdbcOperations.queryForInt(CUSTOMER_COUNT);

        // then
        Assert.assertEquals(2, numberOfCustomers);
    }

    /**
     * Test ensures that different test data can be loaded.
     */
    @Test
    @Load("classpath:/testdata/mafia.sql")
    public void shouldCountOneCustomer() {
        // given

        // when
        final int numberOfCustomers = this.jdbcOperations.queryForInt(CUSTOMER_COUNT);

        // then
        Assert.assertEquals(1, numberOfCustomers);
    }

}
