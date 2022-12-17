package io.akenza.client.v3;

import io.akenza.client.http.HttpOptions;
import io.akenza.client.http.RateLimitInterceptor;
import io.akenza.client.v3.domain.aggregations.AggregationClient;
import io.akenza.client.v3.domain.custom_fields.CustomFieldClient;
import io.akenza.client.v3.domain.data.DataQueryClient;
import io.akenza.client.v3.domain.data_flows.DataFlowClient;
import io.akenza.client.v3.domain.device_connectors.DeviceConnectorClient;
import io.akenza.client.v3.domain.device_credentials.DeviceCredentialsClient;
import io.akenza.client.v3.domain.device_types.DeviceTypeClient;
import io.akenza.client.v3.domain.devices.DeviceClient;
import io.akenza.client.v3.domain.downlinks.DownlinkClient;
import io.akenza.client.v3.domain.operations.OperationClient;
import io.akenza.client.v3.domain.organizations.OrganizationClient;
import io.akenza.client.v3.domain.output_connectors.OutputConnectorClient;
import io.akenza.client.v3.domain.rules.RuleClient;
import io.akenza.client.v3.domain.tags.TagClient;
import io.akenza.client.v3.domain.workspaces.WorkspaceClient;
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
     * Whether to enable the current HTTP logger for every Request, Response and other sensitive information.
     *
     * @param enabled whether to enable the HTTP logger or not.
     */
    public void setLoggingEnabled(boolean enabled) {
        logging.setLevel(enabled ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
    }

    public WorkspaceClient workspaces() {
        return new WorkspaceClient(client, baseUrl, apiKey);
    }

    public OrganizationClient organizations() {
        return new OrganizationClient(client, baseUrl, apiKey);
    }

    public DataQueryClient dataQuery() {
        return new DataQueryClient(client, baseUrl, apiKey);
    }

    public DeviceClient devices() {
        return new DeviceClient(client, baseUrl, apiKey);
    }

    public DeviceTypeClient deviceTypes() {
        return new DeviceTypeClient(client, baseUrl, apiKey);
    }

    public DeviceConnectorClient deviceConnectors() {
        return new DeviceConnectorClient(client, baseUrl, apiKey);
    }

    public OutputConnectorClient outputConnectors() {
        return new OutputConnectorClient(client, baseUrl, apiKey);
    }

    public DataFlowClient dataFlows() {
        return new DataFlowClient(client, baseUrl, apiKey);
    }

    public AggregationClient aggregations() {
        return new AggregationClient(client, baseUrl, apiKey);
    }

    public DownlinkClient downlinks() {
        return new DownlinkClient(client, baseUrl, apiKey);
    }

    public DeviceCredentialsClient deviceCredentials() {
        return new DeviceCredentialsClient(client, baseUrl, apiKey);
    }

    public CustomFieldClient customFields() {
        return new CustomFieldClient(client, baseUrl, apiKey);
    }

    public TagClient tags() {
        return new TagClient(client, baseUrl, apiKey);
    }

    public OperationClient operations() {
        return new OperationClient(client, baseUrl, apiKey);
    }

    public RuleClient rules() {
        return new RuleClient(client, baseUrl, apiKey);
    }
//
//    public ScriptRunnerClient scriptRunner() {
//        return new ScriptRunnerClient(client, baseUrl, apiKey);
//    }
//
//    public DeviceConfigurationClient deviceConfiguration() {
//        return new DeviceConfigurationClient(client, baseUrl, apiKey);
//    }
//
//    public CustomLogicBlockClient customLogicBlocks() {
//        return new CustomLogicBlockClient(client, baseUrl, apiKey);
//    }
}
