package io.akenza.client.v3;

import io.akenza.client.http.*;
import io.akenza.client.v3.domain.aggregations.AggregationClient;
import io.akenza.client.v3.domain.custom_fields.CustomFieldClient;
import io.akenza.client.v3.domain.custom_logic_blocks.CustomLogicBlockClient;
import io.akenza.client.v3.domain.data.DataQueryClient;
import io.akenza.client.v3.domain.data_flows.DataFlowClient;
import io.akenza.client.v3.domain.device_configuration.DeviceConfigurationClient;
import io.akenza.client.v3.domain.device_connectors.DeviceConnectorClient;
import io.akenza.client.v3.domain.device_credentials.DeviceCredentialClient;
import io.akenza.client.v3.domain.device_types.DeviceTypeClient;
import io.akenza.client.v3.domain.devices.DeviceClient;
import io.akenza.client.v3.domain.downlinks.DownlinkClient;
import io.akenza.client.v3.domain.operations.OperationClient;
import io.akenza.client.v3.domain.organizations.OrganizationClient;
import io.akenza.client.v3.domain.output_connectors.OutputConnectorClient;
import io.akenza.client.v3.domain.rules.RuleClient;
import io.akenza.client.v3.domain.script_runner.ScriptRunnerClient;
import io.akenza.client.v3.domain.tags.TagClient;
import io.akenza.client.v3.domain.workspaces.WorkspaceClient;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class AkenzaAPI {

    private final TelemetryInterceptor telemetry;
    private final HttpOptions options;

    private final OkHttpClient client;
    private final HttpLoggingInterceptor logging;

    public AkenzaAPI(HttpOptions options) {
        this.options = options;
        Objects.requireNonNull(options, "options must not be null");

        logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        telemetry = new TelemetryInterceptor();

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        final ProxyOptions proxyOptions = options.proxyOptions();
        if (proxyOptions != null) {
            clientBuilder.proxy(proxyOptions.getProxy());
            final String proxyAuth = proxyOptions.getBasicAuthentication();
            if (proxyAuth != null) {
                clientBuilder.proxyAuthenticator(new Authenticator() {
                    private static final String PROXY_AUTHORIZATION_HEADER = "Proxy-Authorization";

                    @Override
                    public okhttp3.Request authenticate(Route route, @Nonnull Response response) throws IOException {
                        if (response.request().header(PROXY_AUTHORIZATION_HEADER) != null) {
                            return null;
                        }
                        return response.request().newBuilder()
                                .header(PROXY_AUTHORIZATION_HEADER, proxyAuth)
                                .build();
                    }
                });
            }
        }

        Dispatcher dispatcher = new Dispatcher();
        // maximum number of requests to execute concurrently
        // https://square.github.io/okhttp/4.x/okhttp/okhttp3/-dispatcher/max-requests/
        dispatcher.setMaxRequests(options.maxRequests());
        // maximum number of requests for each host to execute concurrently
        // https://square.github.io/okhttp/4.x/okhttp/okhttp3/-dispatcher/max-requests-per-host/
        dispatcher.setMaxRequestsPerHost(options.maxRequestsPerHost());

        this.client = clientBuilder
                .addInterceptor(logging)
                .addInterceptor(telemetry)
                .addInterceptor(new RateLimitInterceptor(options.maxRetries()))
                .connectTimeout(options.connectTimeout(), TimeUnit.SECONDS)
                .readTimeout(options.readTimeout(), TimeUnit.SECONDS)
                .dispatcher(dispatcher)
                .build();
    }

    /**
     * Creates an instance of the akenza client for the given baseUrl and API key.
     *
     * @param baseUrl the akenza private cloud domain
     * @param apiKey  the API key to authenticate the calls with
     */
    public static AkenzaAPI create(String baseUrl, String apiKey) {
        return new AkenzaAPI(ImmutableHttpOptions.builder()
                .baseUrl(baseUrl)
                .authOptions(ImmutableAuthOptions.builder()
                        .apiKey(apiKey)
                        .build())
                .build());
    }

    /**
     * Creates an instance of the akenza client with a given API key.
     *
     * @param apiKey the API key to authenticate the calls with
     */
    public static AkenzaAPI create(String apiKey) {
        return new AkenzaAPI(ImmutableHttpOptions.builder()
                .authOptions(ImmutableAuthOptions.builder()
                        .apiKey(apiKey).build())
                .build());
    }

    /**
     * Creates an instance of the akenza client for the given HttpOptions
     *
     * @param options the HttpOptions to use
     */
    public static AkenzaAPI create(HttpOptions options) {
        return new AkenzaAPI(options);
    }

    /**
     * Whether to enable the current HTTP logger for every Request, Response and other sensitive information.
     *
     * @param enabled whether to enable the HTTP logger or not.
     */
    public void setLoggingEnabled(boolean enabled) {
        logging.setLevel(enabled ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
    }

    /**
     * Opts out of adding telemetry information to every request done by this SDK
     */
    public void doNotSendTelemetry() {
        telemetry.setEnabled(false);
    }

    public WorkspaceClient workspaces() {
        return new WorkspaceClient(client, options);
    }

    public OrganizationClient organizations() {
        return new OrganizationClient(client, options);
    }

    public DataQueryClient dataQuery() {
        return new DataQueryClient(client, options);
    }

    public DeviceClient devices() {
        return new DeviceClient(client, options);
    }

    public DeviceTypeClient deviceTypes() {
        return new DeviceTypeClient(client, options);
    }

    public DeviceConnectorClient deviceConnectors() {
        return new DeviceConnectorClient(client, options);
    }

    public OutputConnectorClient outputConnectors() {
        return new OutputConnectorClient(client, options);
    }

    public DataFlowClient dataFlows() {
        return new DataFlowClient(client, options);
    }

    public AggregationClient aggregations() {
        return new AggregationClient(client, options);
    }

    public DownlinkClient downlinks() {
        return new DownlinkClient(client, options);
    }

    public DeviceCredentialClient deviceCredentials() {
        return new DeviceCredentialClient(client, options);
    }

    public CustomFieldClient customFields() {
        return new CustomFieldClient(client, options);
    }

    public TagClient tags() {
        return new TagClient(client, options);
    }

    public OperationClient operations() {
        return new OperationClient(client, options);
    }

    public RuleClient rules() {
        return new RuleClient(client, options);
    }

    public ScriptRunnerClient scripts() {
        return new ScriptRunnerClient(client, options);
    }

    public DeviceConfigurationClient deviceConfigurations() {
        return new DeviceConfigurationClient(client, options);
    }

    public CustomLogicBlockClient customLogicBlocks() {
        return new CustomLogicBlockClient(client, options);
    }
}
