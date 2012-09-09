/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.junit.testdata.spring;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.github.sebhoss.junit.testdata.Evaluator;
import com.github.sebhoss.junit.testdata.ExecutionPoint;
import com.github.sebhoss.junit.testdata.SuppliedWritingEvaluator;

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
     * @return An evaluator which reads & writes SQL statements before the test is executed.
     */
    @Bean
    public Evaluator sqlBeforeStatementEvaluator() {
        return new SuppliedWritingEvaluator<>(this.supplier.loadSqlSupplier(), this.sql.batchSqlWriter(),
                ExecutionPoint.BEFORE_STATEMENT);
    }

}
