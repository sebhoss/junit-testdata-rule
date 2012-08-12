package com.github.sebhoss.junit.testdata.spring;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import com.github.sebhoss.testdata.Evaluator;

/**
 * Default Spring-configuration for the Load annotation for loading SQL data from <em>.sql</em> files.
 */
@Configuration
@Import(EvaluatorConfiguration.class)
public class DefaultLoadSqlConfiguration extends AbstractTestDataRuleConfiguration {

    @Inject
    private PlatformTransactionManager transactionManager;

    @Inject
    private EvaluatorConfiguration     evaluators;

    @Override
    public Evaluator evaluator() {
        return new TransactionalEvaluator(this.transactionManager, this.evaluators.sqlBeforeStatementEvaluator());
    }

}
