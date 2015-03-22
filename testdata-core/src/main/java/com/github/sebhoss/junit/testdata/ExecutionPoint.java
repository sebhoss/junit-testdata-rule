/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata;

/**
 * Possible points of time where test data should be loaded (a.k.a. "execution point").
 */
public enum ExecutionPoint {

    /** Signals that the test data must be written after the statement was evaluated. */
    AFTER_STATEMENT,

    /** Signals that the test data must be written before the statement was evaluated. */
    BEFORE_STATEMENT;

}
