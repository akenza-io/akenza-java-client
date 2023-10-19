package io.akenza.client;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static java.nio.charset.Charset.defaultCharset;

public class TestUtils {

    private static final String FIXTURE_ROOT = "io/akenza/client/v3/domain/";

    public static String getFixture(final String path) {
        try (var inputStream = TestUtils.class.getClassLoader().getResourceAsStream(FIXTURE_ROOT + path)) {
            return new String(inputStream.readAllBytes(), defaultCharset());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Return a File pointing to the resource on the classpath
     */
    public static File loadFile(final String path) {
        try {
            URL resource = Paths.get(FIXTURE_ROOT + path).toUri().toURL();
            return new File(resource.toURI());
        } catch (URISyntaxException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
