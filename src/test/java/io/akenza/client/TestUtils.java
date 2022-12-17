package io.akenza.client;

import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.net.URL;

import static com.google.common.io.Resources.getResource;
import static java.nio.charset.Charset.defaultCharset;

public class TestUtils {

    private static final String FIXTURE_ROOT = "io/akenza/client/v3/domain/";

    public static String getFixture(final String path) {
        try {
            return Resources.toString(getResource(FIXTURE_ROOT + path), defaultCharset());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Return a File pointing to the resource on the classpath
     */
    public static File loadFile(final String path) {
        URL resource = getResource(FIXTURE_ROOT + path);
        try {
            return new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
