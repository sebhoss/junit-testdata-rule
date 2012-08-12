package com.github.sebhoss.testdata;

import java.nio.file.Files;
import java.nio.file.Path;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tests for the {@link Path} API.
 */
@SuppressWarnings({ "nls", "static-method" })
public class PathTest {

    /**
     * @throws Exception
     *             In case an IO exception occurs.
     */
    @Test
    public void findLocalSqlFile() throws Exception {
        byte[] contentInBytes = Files.readAllBytes(SqlFiles.CREATE_HANS.path);

        Assert.assertNotNull(contentInBytes);
    }

    /**
     * @throws Exception
     *             In case an IO exception occurs.
     */
    @Test
    public void readContents() throws Exception {
        final byte[] contentInBytes = Files.readAllBytes(SqlFiles.CREATE_HANS.path);
        final String content = new String(contentInBytes);

        Assert.assertEquals("INSERT INTO customer (id, name) values (1, 'hans');", content.trim());
    }

}
