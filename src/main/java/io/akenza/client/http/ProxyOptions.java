package io.akenza.client.http;

import okhttp3.Credentials;

import java.net.Proxy;
import java.util.Objects;

/**
 * Configure Java Proxy-related settings.
 */
public class ProxyOptions {
    private final Proxy proxy;
    private String basicAuth;

    /**
     * Builds a new instance using the given Proxy.
     * The Proxy will not have authentication unless {@link #setBasicAuthentication(String, char[])} is set.
     *
     * @param proxy the Proxy to use.
     */
    public ProxyOptions(Proxy proxy) {
        Objects.requireNonNull(proxy, "proxy");
        this.proxy = proxy;
    }

    /**
     * Set the authentication value to use for this Proxy.
     *
     * @param username the username to use.
     * @param password the password to use.
     */
    public void setBasicAuthentication(String username, char[] password) {
        Objects.requireNonNull(proxy, "username");
        Objects.requireNonNull(proxy, "password");
        this.basicAuth = Credentials.basic(username, new String(password));
    }

    /**
     * Proxy instance to set
     *
     * @return the Proxy instance to set
     */
    public Proxy getProxy() {
        return proxy;
    }

    /**
     * Authentication value to use for this Proxy
     *
     * @return the authentication value to use for this Proxy, or null if unset
     */
    public String getBasicAuthentication() {
        return basicAuth;
    }
}