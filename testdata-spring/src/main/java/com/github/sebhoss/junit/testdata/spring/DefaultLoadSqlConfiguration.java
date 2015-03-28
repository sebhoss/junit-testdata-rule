/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata.spring;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import com.github.sebhoss.junit.testdata.Evaluator;

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
        return new SpringTransactionalEvaluator(this.transactionManager, this.evaluators.sqlEvaluator());
    }

}
