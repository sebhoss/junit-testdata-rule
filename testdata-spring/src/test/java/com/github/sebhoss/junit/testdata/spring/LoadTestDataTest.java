/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata.spring;

import javax.inject.Inject;

import com.github.sebhoss.junit.testdata.Load;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test for the default Spring configuration.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DefaultLoadSqlConfiguration.class, TestDatabaseConfiguration.class })
public final class LoadTestDataTest {

    private static final String CUSTOMER_COUNT = "SELECT COUNT(*) FROM customers"; //$NON-NLS-1$

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
        final Integer numberOfCustomers = jdbcOperations.queryForObject(CUSTOMER_COUNT, Integer.class);

        // then
        Assert.assertEquals(2, numberOfCustomers.intValue());
    }

    /**
     * Test ensures that test data is loaded in isolation.
     */
    @Test
    public void shouldCountNoCustomers() {
        // given

        // when
        final Integer numberOfCustomers = jdbcOperations.queryForObject(CUSTOMER_COUNT, Integer.class);

        // then
        Assert.assertEquals(0, numberOfCustomers.intValue());
    }

    /**
     * Test ensures that test data can be loaded multiple times.
     */
    @Test
    @Load("classpath:/testdata/new customers.sql")
    public void shouldCountTwoCustomersAgain() {
        // given

        // when
        final Integer numberOfCustomers = jdbcOperations.queryForObject(CUSTOMER_COUNT, Integer.class);

        // then
        Assert.assertEquals(2, numberOfCustomers.intValue());
    }

    /**
     * Test ensures that different test data can be loaded.
     */
    @Test
    @Load("classpath:/testdata/mafia.sql")
    public void shouldCountOneCustomer() {
        // given

        // when
        final Integer numberOfCustomers = jdbcOperations.queryForObject(CUSTOMER_COUNT, Integer.class);

        // then
        Assert.assertEquals(1, numberOfCustomers.intValue());
    }

}
