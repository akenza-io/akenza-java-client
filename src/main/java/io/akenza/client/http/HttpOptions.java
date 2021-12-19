package io.akenza.client.http;

/**
 * Configures the AkenzaAPI client.
 */
public class HttpOptions {
    private int connectTimeout = 10;
    private int readTimeout = 10;
    private int maxRetries = 3;

    /**
     * @return the connect timeout, in seconds
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * Sets the value of the connect timeout, in seconds. Defaults to ten seconds. A value of zero results in no connect timeout.
     * Negative numbers will be treated as zero.
     *
     * @param connectTimeout the value of the connect timeout to use.
     */
    public void setConnectTimeout(int connectTimeout) {
        if (connectTimeout < 0) {
            connectTimeout = 0;
        }
        this.connectTimeout = connectTimeout;
    }

    /**
     * @return the read timeout, in seconds
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     * Sets the value of the read timeout, in seconds. Defaults to ten seconds. A value of zero results in no read timeout.
     * Negative numbers will be treated as zero.
     *
     * @param readTimeout the value of the read timeout to use.
     */
    public void setReadTimeout(int readTimeout) {
        if (readTimeout < 0) {
            readTimeout = 0;
        }
        this.readTimeout = readTimeout;
    }

    /**
     * @return the configured number of maximum retries to attempt when a rate-limit error is encountered.
     */
    public int getMaxRetries() {
        return maxRetries;
    }

    /**
     * Sets the maximum number of consecutive retries for Akenza API requests that fail due to rate-limits being reached.
     * By default, rate-limited requests will be retries a maximum of three times. To disable retries on rate-limit
     * errors, set this value to zero.
     *
     * @param maxRetries the maximum number of consecutive retries to attempt upon a rate-limit error. Defaults to three.
     *                   Must be a number between zero (do not retry) and ten.
     */
    public void setMaxRetries(int maxRetries) {
        if (maxRetries < 0 || maxRetries > 10) {
            throw new IllegalArgumentException("Retries must be between zero and ten.");
        }
        this.maxRetries = maxRetries;
    }
}