/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata.guice;

import java.nio.charset.Charset;

import javax.inject.Singleton;
import javax.transaction.TransactionManager;

import com.github.sebhoss.junit.testdata.BatchSqlWriter;
import com.github.sebhoss.junit.testdata.Evaluator;
import com.github.sebhoss.junit.testdata.ExecutionPoint;
import com.github.sebhoss.junit.testdata.Load;
import com.github.sebhoss.junit.testdata.LoadLocator;
import com.github.sebhoss.junit.testdata.Locator;
import com.github.sebhoss.junit.testdata.PathBasedStatementReader;
import com.github.sebhoss.junit.testdata.Reader;
import com.github.sebhoss.junit.testdata.ReadingSupplier;
import com.github.sebhoss.junit.testdata.StatementReader;
import com.github.sebhoss.junit.testdata.SuppliedWritingEvaluator;
import com.github.sebhoss.junit.testdata.Supplier;
import com.github.sebhoss.junit.testdata.TransactionalEvaluator;
import com.github.sebhoss.junit.testdata.Writer;
import com.google.common.base.Charsets;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;

/**
 * Default Guice module for the {@link Load} annotation.
 */
public class DefaultLoadModule extends AbstractModule {

    @SuppressWarnings("synthetic-access")
    @Override
    protected void configure() {
        // Evaluator
        this.bind(new StringEvaluator());

        // Supplier
        this.bind(new StringSupplier()).to(new StringReadingSupplier());

        // Annotation
        this.bind(Locator.class).to(LoadLocator.class);

        // Reader
        this.bind(new StringReader()).to(PathBasedStatementReader.class);

        // Statements
        this.bind(StatementReader.class);
        this.bind(Charset.class).toInstance(Charsets.UTF_8);
        this.bind(String.class).toInstance(";"); //$NON-NLS-1$

        // Writer
        this.bind(new StringWriter()).to(BatchSqlWriter.class);

        // Execution Point
        this.bind(ExecutionPoint.class).toInstance(ExecutionPoint.BEFORE_STATEMENT);
    }

    /**
     * @param transactionManager
     *            The transaction manager to use.
     * @param evaluator
     *            The evaluator to use.
     * @return A transactional evaluator.
     */
    @SuppressWarnings("static-method")
    @Provides
    @Singleton
    public Evaluator transactionalEvaluator(final TransactionManager transactionManager,
            final SuppliedWritingEvaluator<String> evaluator) {
        return new TransactionalEvaluator(transactionManager, evaluator);
    }

    private static final class StringEvaluator extends TypeLiteral<SuppliedWritingEvaluator<String>> {
        // Guice
    }

    private static final class StringSupplier extends TypeLiteral<Supplier<String>> {
        // Guice
    }

    private static final class StringReadingSupplier extends TypeLiteral<ReadingSupplier<String>> {
        // Guice
    }

    private static final class StringReader extends TypeLiteral<Reader<String>> {
        // Guice
    }

    private static final class StringWriter extends TypeLiteral<Writer<String>> {
        // Guice
    }

}
