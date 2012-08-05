package com.github.sebhoss.testdata.spring;

import java.lang.annotation.Annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.sebhoss.testdata.TestDataEvaluator;
import com.github.sebhoss.testdata.TestDataReader;
import com.github.sebhoss.testdata.impl.TestDataRule;

@Configuration
public abstract class AbstractTestDataConfiguration<I extends Annotation, O extends Object> {

    @Bean
    public abstract TestDataRule<I, O> rule();

    @Bean
    public abstract TestDataReader<I, O> reader();

    @Bean
    public abstract TestDataEvaluator<O> evaluator();

}
