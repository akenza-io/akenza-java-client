package io.akenza.client.http;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Representation of the client information sent by this SDK on every request.
 * <p>
 * This class is thread-safe.
 *
 * @see TelemetryInterceptor
 */
public class Telemetry {

    private static final Logger LOGGER = LoggerFactory.getLogger(Telemetry.class);
    static final String HEADER_NAME = "Akenza-Client";

    private static final String JAVA_SPECIFICATION_VERSION = "java.specification.version";
    private static final String NAME_KEY = "name";
    private static final String VERSION_KEY = "version";
    private static final String LIBRARY_VERSION_KEY = "akenza-java-client";
    private static final String ENV_KEY = "env";
    private static final String JAVA_KEY = "java";

    private final String name;
    private final String version;
    private final String libraryVersion;
    private final Map<String, String> env;
    private final String value;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public Telemetry(String name, String version) {
        this(name, version, null);
    }

    public Telemetry(String name, String version, String libraryVersion) {
        this.name = name;
        this.version = version;
        this.libraryVersion = libraryVersion;

        if (name == null) {
            env = Collections.emptyMap();
            value = null;
            return;
        }

        Map<String, Object> values = new HashMap<>();
        values.put(NAME_KEY, name);
        if (version != null) {
            values.put(VERSION_KEY, version);
        }

        HashMap<String, String> tmpEnv = new HashMap<>();
        tmpEnv.put(JAVA_KEY, getJDKVersion());
        if (libraryVersion != null) {
            tmpEnv.put(LIBRARY_VERSION_KEY, libraryVersion);
        }
        this.env = Collections.unmodifiableMap(tmpEnv);
        values.put(ENV_KEY, env);

        String tmpValue;
        try {
            String json = objectMapper.writeValueAsString(values);
            tmpValue = Base64.getUrlEncoder().encodeToString(json.getBytes());
        } catch (JsonProcessingException e) {
            tmpValue = null;
            LOGGER.error("Failed to serialize properties", e);
        }
        value = tmpValue;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    //Visible for testing
    String getLibraryVersion() {
        return libraryVersion;
    }

    //Visible for testing
    Map<String, String> getEnvironment() {
        return env;
    }

    public String getValue() {
        return value;
    }

    private String getJDKVersion() {
        String jdkVersion;
        try {
            jdkVersion = System.getProperty(JAVA_SPECIFICATION_VERSION);
        } catch (Exception ignored) {
            jdkVersion = Runtime.class.getPackage().getSpecificationVersion();
        }
        return jdkVersion;
    }

}