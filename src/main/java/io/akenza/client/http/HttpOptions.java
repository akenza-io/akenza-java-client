package io.akenza.client.http;

import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Preconditions;
import org.immutables.value.Value;

import javax.annotation.Nullable;

/**
 * Configures the AkenzaAPI HTTP client
 */
@Value.Immutable
@AkenzaStyle
public abstract class HttpOptions {
    public static final String DEFAULT_BASE_URL = "https://api.akenza.io";

    /**
     * Proxy configuration options
     */
    @Nullable
    public abstract ProxyOptions proxyOptions();

    /**
     * Auth options
     */
    public abstract AuthOptions authOptions();

    /**
     * Akenza API base url to use, used for configuring private cloud domains
     * <p>
     * Defaults to the akenza public cloud.
     */
    @Value.Default
    public String baseUrl() {
        return DEFAULT_BASE_URL;
    }

    /**
     * Maximum number of requests to execute concurrently
     * <p>
     * Defaults to 64.
     */
    @Value.Default
    public Integer maxRequests() {
        return 64;
    }

    /**
     * Maximum number of requests for each host to execute concurrently
     * <p>
     * Defaults to 5.
     */
    @Value.Default
    public Integer maxRequestsPerHost() {
        return 5;
    }

    /**
     * The connect timeout, in seconds.
     * <p>
     * Defaults to ten seconds. A value of zero results in no connect timeout.
     */
    @Value.Default
    public Integer connectTimeout() {
        return 10;
    }


    /**
     * The value of the read timeout, in seconds.
     * <p>
     * Defaults to ten seconds. A value of zero results in no read timeout.
     */
    @Value.Default
    public Integer readTimeout() {
        return 10;
    }

    /**
     * Maximum number of consecutive retries for Akenza API requests that fail due to rate-limits being reached.
     * By default, rate-limited requests will be retried a maximum of three times. To disable retries on rate-limit
     * errors, set this value to zero.
     * <p>
     * Defaults to three. Must be a number between zero (do not retry) and ten.
     */
    @Value.Default
    public Integer maxRetries() {
        return 3;
    }

    @Value.Check
    protected void check() {
        Preconditions.checkState(maxRequests() >= 1, "maxRequests must be one or greater");
        Preconditions.checkState(maxRequestsPerHost() >= 1, "maxRequestsPerHost must be one or greater");
        Preconditions.checkState(maxRetries() >= 0 && maxRetries() <= 10, "maxRetries must be between zero and ten");
        Preconditions.checkState(readTimeout() >= 0, "readTimeout must be positive");
        Preconditions.checkState(connectTimeout() >= 0, "connectTimeout must be positive");
    }
}