/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Description;

/**
 * Delegates its calls to a supplied list of {@link Locator}s.
 */
public final class DelegatingLocator implements Locator {

    private final List<Locator> locators;

    /**
     * @param locators
     *            The locators to call
     */
    public DelegatingLocator(final List<Locator> locators) {
        this.locators = locators;
    }

    @Override
    public boolean canAccess(final Description description) {
        return locators.stream().anyMatch(locator -> locator.canAccess(description));
    }

    @Override
    public List<String> locateFilesToLoad(final Description description) {
        return locators.stream()
                .map(locator -> locator.locateFilesToLoad(description))
                .collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
    }

}
