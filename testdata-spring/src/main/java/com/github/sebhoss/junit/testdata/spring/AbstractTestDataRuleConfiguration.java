/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata.spring;

import com.github.sebhoss.junit.testdata.Evaluator;
import com.github.sebhoss.junit.testdata.TestData;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * Abstract Spring-configuration. Intended to be an easy way for creating new TestData configurations.
 */
public abstract class AbstractTestDataRuleConfiguration {

    /**
     * @return A test-data rule.
     */
    @Bean
    @ConditionalOnMissingBean(TestData.class)
    public TestData testData() {
        return new TestData(evaluator());
    }

    /**
     * @return A test-data evaluator.
     */
    @Bean
    public abstract Evaluator evaluator();

}
