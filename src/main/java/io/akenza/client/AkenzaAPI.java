package io.akenza.client;

import io.akenza.client.client.DataQueryClient;
import io.akenza.client.client.DeviceClient;
import io.akenza.client.client.WorkspaceClient;
import io.akenza.client.http.HttpOptions;
import io.akenza.client.http.RateLimitInterceptor;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class AkenzaAPI {
    private static final String DEFAULT_BASE_URL = "https://api.akenza.io";

    private final HttpUrl baseUrl;
    private String apiKey;

    private final OkHttpClient client;
    private final HttpLoggingInterceptor logging;

    public AkenzaAPI(String baseUrl, String apiKey, HttpOptions options) {
        Objects.requireNonNull(baseUrl, "baseUrl must not be null");
        Objects.requireNonNull(apiKey, "apiKey must not be null");
        Objects.requireNonNull(options, "options must not be null");

        this.baseUrl = HttpUrl.parse(baseUrl);
        this.apiKey = apiKey;

        logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        this.client = clientBuilder
                .addInterceptor(logging)
                .addInterceptor(new RateLimitInterceptor(options.getMaxRetries()))
                .connectTimeout(options.getConnectTimeout(), TimeUnit.SECONDS)
                .readTimeout(options.getReadTimeout(), TimeUnit.SECONDS)
                .build();
    }

    /**
     * Creates an instance of the akenza client for a private cloud baseUrl and API key.
     * Additionally, {@link HttpOptions} can be provided that will be used to configure the underlying HTTP client.
     *
     * @param baseUrl the akenza private cloud domain
     * @param apiKey  the API key to authenticate the calls with
     * @param options configuration options for this client
     * @see #AkenzaAPI(String, String)
     */
    public static AkenzaAPI create(String baseUrl, String apiKey, HttpOptions options) {
        return new AkenzaAPI(baseUrl, apiKey, options);
    }

    public AkenzaAPI(String baseUrl, String apiKey) {
        this(baseUrl, apiKey, new HttpOptions());
    }

    /**
     * Creates an instance of the akenza client for the given baseUrl and API key.
     *
     * @param baseUrl the akenza private cloud domain
     * @param apiKey  the API key to authenticate the calls with
     */
    public static AkenzaAPI create(String baseUrl, String apiKey) {
        return new AkenzaAPI(baseUrl, apiKey);
    }

    public AkenzaAPI(String apiKey, HttpOptions options) {
        this(DEFAULT_BASE_URL, apiKey, options);
    }

    /**
     * Creates an instance of the akenza client with a given API key.
     * Additionally, {@link HttpOptions} can be provided that will be used to configure the underlying HTTP client.
     *
     * @param apiKey  the API key to authenticate the calls with
     * @param options configuration options for this client
     * @see #AkenzaAPI(String)
     */
    public static AkenzaAPI create(String apiKey, HttpOptions options) {
        return new AkenzaAPI(apiKey, options);
    }

    public AkenzaAPI(String apiKey) {
        this(DEFAULT_BASE_URL, apiKey, new HttpOptions());
    }

    /**
     * Creates an instance of the akenza client with a given API key.
     *
     * @param apiKey the API key to authenticate the calls with
     */
    public static AkenzaAPI create(String apiKey) {
        return new AkenzaAPI(apiKey);
    }

    /**
     * Update the API key to use on new calls.
     *
     * @param apiKey the API key to authenticate calls with.
     */
    public void setApiKey(String apiKey) {
        Objects.requireNonNull(apiKey, "api key must not be null");
        this.apiKey = apiKey;
    }

    /**
     * Whether to enable or not the current HTTP Logger for every Request, Response and other sensitive information.
     *
     * @param enabled whether to enable the HTTP logger or not.
     */
    public void setLoggingEnabled(boolean enabled) {
        logging.setLevel(enabled ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
    }

    public WorkspaceClient workspaces() {
        return new WorkspaceClient(client, baseUrl, apiKey);
    }

    public DataQueryClient dataQuery() {
        return new DataQueryClient(client, baseUrl, apiKey);
    }

    public DeviceClient deviceClient() {
        return new DeviceClient(client, baseUrl, apiKey);
    }
}
