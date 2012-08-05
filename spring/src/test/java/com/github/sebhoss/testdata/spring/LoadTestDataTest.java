package com.github.sebhoss.testdata.spring;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.sebhoss.testdata.Load;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DefaultLoadSqlTestDataConfiguration.class, TestDatabaseConfiguration.class })
@SuppressWarnings("nls")
public final class LoadTestDataTest {

    @Inject
    @Rule
    public TestRule       testdata;

    @Inject
    public JdbcOperations jdbcOperations;

    @Test
    @Load("classpath:/testdata/new customers.sql")
    public void shouldCountTwoCustomers() {
        // given
        final String query = "SELECT COUNT(*) FROM customers";

        // when
        final int numberOfCustomers = this.jdbcOperations.queryForInt(query);

        // then
        Assert.assertEquals(2, numberOfCustomers);
    }

    @Test
    public void shouldCountNoCustomers() {
        // given
        final String query = "SELECT COUNT(*) FROM customers";

        // when
        final int numberOfCustomers = this.jdbcOperations.queryForInt(query);

        // then
        Assert.assertEquals(0, numberOfCustomers);
    }

    @Test
    @Load("classpath:/testdata/new customers.sql")
    public void shouldCountTwoCustomersAgain() {
        // given
        final String query = "SELECT COUNT(*) FROM customers";

        // when
        final int numberOfCustomers = this.jdbcOperations.queryForInt(query);

        // then
        Assert.assertEquals(2, numberOfCustomers);
    }

    @Test
    @Load("classpath:/testdata/mafia.sql")
    public void shouldCountOneCustomer() {
        // given
        final String query = "SELECT COUNT(*) FROM customers";

        // when
        final int numberOfCustomers = this.jdbcOperations.queryForInt(query);

        // then
        Assert.assertEquals(1, numberOfCustomers);
    }

}
