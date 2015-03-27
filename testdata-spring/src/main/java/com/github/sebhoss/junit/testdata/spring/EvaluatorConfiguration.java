/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata.spring;

import javax.inject.Inject;

import com.github.sebhoss.junit.testdata.Evaluator;
import com.github.sebhoss.junit.testdata.ExecutionPoint;
import com.github.sebhoss.junit.testdata.SuppliedWritingEvaluator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring-configuration for {@link Evaluator}s.
 */
@Configuration
@Import({ WriterConfiguration.class, SupplierConfiguration.class })
public class EvaluatorConfiguration {

    @Inject
    private WriterConfiguration   sql;

    @Inject
    private SupplierConfiguration supplier;

    /**
     * @return An evaluator which reads and writes SQL statements before the test is executed.
     */
    @Bean
    @ConditionalOnMissingBean(Evaluator.class)
    public Evaluator sqlBeforeStatementEvaluator() {
        return new SuppliedWritingEvaluator<>(supplier.loadSqlSupplier(),
                sql.batchSqlWriter(), ExecutionPoint.BEFORE_STATEMENT);
    }

}
