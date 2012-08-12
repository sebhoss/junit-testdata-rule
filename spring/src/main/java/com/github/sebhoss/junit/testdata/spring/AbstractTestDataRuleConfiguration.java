package com.github.sebhoss.junit.testdata.spring;

import org.springframework.context.annotation.Bean;

import com.github.sebhoss.testdata.Evaluator;
import com.github.sebhoss.testdata.impl.TestDataRule;

/**
 * Abstract Spring-configuration. Intended to be an easy way for creating new TestData configurations.
 */
public abstract class AbstractTestDataRuleConfiguration {

    /**
     * @return A test-data rule.
     */
    @Bean
    public TestDataRule rule() {
        return new TestDataRule(this.evaluator());
    }

    /**
     * @return A test-data evaluator.
     */
    @Bean
    public abstract Evaluator evaluator();

}
