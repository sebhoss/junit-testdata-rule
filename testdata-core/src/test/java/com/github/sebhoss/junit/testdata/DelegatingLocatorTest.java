/*
 * Copyright © 2012 Sebastian Hoß <mail@shoss.de>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package com.github.sebhoss.junit.testdata;

import static com.google.common.collect.Iterables.concat;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.truth0.Truth.ASSERT;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;

/**
 * Tests for {@link DelegatingLocator}
 */
@SuppressWarnings("boxing")
public class DelegatingLocatorTest {

    private Description description;

    /**
     * Creates a JUnit {@link Description} instance which is consumed by the tests below
     */
    @Before
    public void setUp() {
        description = mock(Description.class);
    }

    /**
     * Ensures that the delegators accepts a list of single locators for constructions
     */
    @SuppressWarnings("static-method")
    @Test
    public void shouldAcceptListOfLocators() {
        // given
        final Locator locator1 = mock(Locator.class);
        final Locator locator2 = mock(Locator.class);
        final Locator locator3 = mock(Locator.class);

        // when
        final Locator delegator = delegator(locator1, locator2, locator3);

        // then
        ASSERT.that(delegator).isNotNull();
    }

    /**
     * Ensures that the given locator is called by the delegator for the {@link Locator#canAccess(Description)} method
     */
    @Test
    public void shouldCallGivenLocatorForAccessCheck() {
        // given
        final Locator locator = mock(Locator.class);
        given(locator.canAccess(description)).willReturn(Boolean.TRUE);

        // when
        final boolean canAccess = delegator(locator).canAccess(description);

        // then
        ASSERT.that(canAccess).isTrue();
    }

    /**
     * Ensures that the delegator short curcuits the call to {@link Locator#canAccess(Description)}
     */
    @Test
    public void shouldShortCurcuitCanAccessCall() {
        // given
        final Locator locator1 = mock(Locator.class);
        final Locator locator2 = mock(Locator.class);
        final Locator locator3 = mock(Locator.class);
        given(locator1.canAccess(description)).willReturn(Boolean.TRUE);
        given(locator2.canAccess(description)).willReturn(Boolean.TRUE);
        given(locator3.canAccess(description)).willReturn(Boolean.TRUE);

        // when
        delegator(locator1, locator2, locator3).canAccess(description);

        // then
        then(locator1).should(only()).canAccess(description);
        verifyZeroInteractions(locator2);
        verifyZeroInteractions(locator3);
    }

    /**
     * Ensures that the delegator short curcuits the call to {@link Locator#canAccess(Description)}
     */
    @Test
    public void shouldShortCurcuitCanAccessCall2() {
        // given
        final Locator locator1 = mock(Locator.class);
        final Locator locator2 = mock(Locator.class);
        final Locator locator3 = mock(Locator.class);
        given(locator1.canAccess(description)).willReturn(Boolean.FALSE);
        given(locator2.canAccess(description)).willReturn(Boolean.TRUE);
        given(locator3.canAccess(description)).willReturn(Boolean.TRUE);

        // when
        delegator(locator1, locator2, locator3).canAccess(description);

        // then
        then(locator1).should(only()).canAccess(description);
        then(locator2).should(only()).canAccess(description);
        verifyZeroInteractions(locator3);
    }

    /**
     * Ensures that the delegator short curcuits the call to {@link Locator#canAccess(Description)}
     */
    @Test
    public void shouldShortCurcuitCanAccessCall3() {
        // given
        final Locator locator1 = mock(Locator.class);
        final Locator locator2 = mock(Locator.class);
        final Locator locator3 = mock(Locator.class);
        given(locator1.canAccess(description)).willReturn(Boolean.FALSE);
        given(locator2.canAccess(description)).willReturn(Boolean.FALSE);
        given(locator3.canAccess(description)).willReturn(Boolean.TRUE);

        // when
        delegator(locator1, locator2, locator3).canAccess(description);

        // then
        then(locator1).should(only()).canAccess(description);
        then(locator2).should(only()).canAccess(description);
        then(locator3).should(only()).canAccess(description);
    }

    /**
     * Ensures that the given locator is called by the delegator for the {@link Locator#locateTestData(Description)}
     * method
     */
    @Test
    public void shouldCallGivenLocatorForFileLocations() {
        // given
        final List<String> locations = new ArrayList<>();
        final Locator locator = mock(Locator.class);
        given(locator.locateTestData(description)).willReturn(locations.stream());

        // when
        final Stream<String> fileLocations = delegator(locator).locateTestData(description);

        // then
        ASSERT.that(fileLocations.collect(toList())).containsExactlyElementsIn(locations).inOrder();
    }

    /**
     * Ensures that the delegator calls all given locators in order to get locations
     */
    @Test
    public void shouldCallAllGivenLocatorsForFileLocations() {
        // given
        final List<String> locations1 = new ArrayList<>();
        final List<String> locations2 = new ArrayList<>();
        final Locator locator1 = mock(Locator.class);
        final Locator locator2 = mock(Locator.class);
        given(locator1.locateTestData(description)).willReturn(locations1.stream());
        given(locator2.locateTestData(description)).willReturn(locations2.stream());

        // when
        final Stream<String> fileLocations = delegator(locator1, locator2).locateTestData(description);

        // then
        ASSERT.that(fileLocations.collect(toList()))
                .containsExactlyElementsIn(concat(locations1, locations2))
                .inOrder();
    }

    /**
     * Ensures that the delegator calls only the matching given locators in order to get locations
     */
    @Test
    public void shouldCallOnlyMatchingGivenLocatorsForFileLocations() {
        // given
        final List<String> locations1 = new ArrayList<>();
        final List<String> locations2 = new ArrayList<>();
        final Locator locator1 = mock(Locator.class);
        final Locator locator2 = mock(Locator.class);
        given(locator1.locateTestData(description)).willReturn(locations1.stream());
        given(locator2.locateTestData(description)).willReturn(locations2.stream());

        // when
        given(locator1.canAccess(description)).willReturn(Boolean.FALSE);
        final Stream<String> fileLocations = delegator(locator1, locator2).locateTestData(description);

        // then
        ASSERT.that(fileLocations.collect(toList())).containsExactlyElementsIn(locations2).inOrder();
    }

    /**
     * Ensures that the delegator calls only the matching given locators in order to get locations
     */
    @Test
    public void shouldCallOnlyMatchingGivenLocatorsForFileLocations2() {
        // given
        final List<String> locations1 = new ArrayList<>();
        final List<String> locations2 = new ArrayList<>();
        final Locator locator1 = mock(Locator.class);
        final Locator locator2 = mock(Locator.class);
        given(locator1.locateTestData(description)).willReturn(locations1.stream());
        given(locator2.locateTestData(description)).willReturn(locations2.stream());

        // when
        given(locator2.canAccess(description)).willReturn(Boolean.FALSE);
        final Stream<String> fileLocations = delegator(locator1, locator2).locateTestData(description);

        // then
        ASSERT.that(fileLocations.collect(toList())).containsExactlyElementsIn(locations1).inOrder();
    }

    private static Locator delegator(final Locator... locators) {
        return new DelegatingLocator(asList(locators));
    }

}
