package io.akenza.client.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * HTTP response with non-200 StatusCode.
 */
public class HttpClientResponseException extends AkenzaException {
    private final int statusCode;
    private final String path;
    private Map<String, Object> values = new HashMap<>();

    /**
     * Response to request came back with non-2xx status code
     *
     * @param path       URI path
     * @param statusCode status of repsonse
     * @param msg        response body
     */
    public HttpClientResponseException(final String path, final int statusCode, final String msg) {
        super(msg);
        this.statusCode = statusCode;
        this.path = path;
    }

    /**
     * Response to request came back with non-2xx status code
     *
     * @param path       URI path
     * @param statusCode status of repsonse
     * @param msg        response body
     * @param cause      exception cause
     */
    public HttpClientResponseException(
            final String path, final int statusCode, final String msg, final Throwable cause) {
        super(msg, cause);
        this.statusCode = statusCode;
        this.path = path;
    }

    public HttpClientResponseException(String path, int statusCode, Map<String, Object> values) {
        super((String) values.get("message"));
        this.statusCode = statusCode;
        this.path = path;
        this.values = values;
    }

    /**
     * Get the status code of the response
     *
     * @return status
     */
    public int statusCode() {
        return statusCode;
    }

    /**
     * Get request URI path
     *
     * @return path
     */
    public String path() {
        return path;
    }

    public Map<String, Object> values() {
        return values;
    }
}
