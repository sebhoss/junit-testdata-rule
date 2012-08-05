package com.github.sebhoss.testdata.spring;

import java.nio.charset.Charset;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.transaction.PlatformTransactionManager;

import com.github.sebhoss.testdata.Load;
import com.github.sebhoss.testdata.TestDataEvaluator;
import com.github.sebhoss.testdata.TestDataExecutionPoint;
import com.github.sebhoss.testdata.TestDataReader;
import com.github.sebhoss.testdata.impl.LoadTestDataLocator;
import com.github.sebhoss.testdata.impl.LoadingTestDataEvaluator;
import com.github.sebhoss.testdata.impl.TestDataRule;

@Configuration
public class DefaultLoadSqlTestDataConfiguration extends AbstractTestDataConfiguration<Load, String> {

    @Inject
    public PlatformTransactionManager transactionManager;

    @Inject
    public JdbcOperations             jdbcTemplate;

    @Override
    public TestDataRule<Load, String> rule() {
        return new TestDataRule<>(Load.class, this.reader(), this.evaluator());
    }

    @Override
    @SuppressWarnings("nls")
    public TestDataReader<Load, String> reader() {
        return new SpringBasedStringReader<>(new LoadTestDataLocator(), new DefaultResourceLoader(),
                Charset.forName("UTF-8"), ";");
    }

    @Override
    public TestDataEvaluator<String> evaluator() {
        return new TransactionalTestDataEvaluator<>(this.transactionManager, new LoadingTestDataEvaluator<>(
                new SpringSqlTestDataLoader(this.jdbcTemplate), TestDataExecutionPoint.BEFORE_STATEMENT));
    }

}
