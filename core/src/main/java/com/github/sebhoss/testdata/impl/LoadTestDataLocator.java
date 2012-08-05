/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */
package com.github.sebhoss.testdata.impl;

import java.util.Arrays;

import com.github.sebhoss.testdata.Load;
import com.github.sebhoss.testdata.TestDataLocator;

public final class LoadTestDataLocator implements TestDataLocator<Load> {

    @Override
    public Iterable<String> locateFilesToLoad(final Load annotation) {
        final String[] locations = annotation.value();

        return Arrays.asList(locations);
    }

}
