package com.github.sebhoss.testdata;

import java.nio.file.Files;

import junit.framework.Assert;

import org.junit.Test;

@SuppressWarnings({ "static-method", "nls" })
public class PathTest {

    @Test
    public void findLocalSqlFile() throws Exception {
        byte[] contentInBytes = Files.readAllBytes(SqlFiles.CREATE_HANS.path);

        Assert.assertNotNull(contentInBytes);
    }

    @Test
    public void readContents() throws Exception {
        final byte[] contentInBytes = Files.readAllBytes(SqlFiles.CREATE_HANS.path);
        final String content = new String(contentInBytes);

        Assert.assertEquals("INSERT INTO customer (id, name) values (1, 'hans');", content.trim());
    }

}
