package io.akenza.client.v3.domain.device_connectors;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.HttpOptions;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import io.akenza.client.utils.BaseClient;
import io.akenza.client.v3.domain.device_connectors.commands.CreateDeviceConnectorCommand;
import io.akenza.client.v3.domain.device_connectors.commands.UpdateDeviceConnectorCommand;
import io.akenza.client.v3.domain.device_connectors.queries.DeviceConnectorFilter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.Map;

/**
 * Client for interacting with device connectors
 */
public class DeviceConnectorClient extends BaseClient {
    private static final String DEVICE_CONNECTOR_URI_TEMPLATE = "v3/device-connectors";
    private static final String DEVICE_CONNECTOR_BY_ID_URI_TEMPLATE = "v3/device-connectors/%s";

    public DeviceConnectorClient(OkHttpClient client, HttpOptions options) {
        super(client, options);
    }

    /**
     * Retrieve a list of device connectors for the specified workspace
     *
     * @param workspaceId the workspace id
     * @param filter      an optional filter for restricting the search and providing pagination parameters
     * @return a page with device connectors
     */
    public Request<DeviceConnectorPage> list(String workspaceId, DeviceConnectorFilter filter) {
        final String path = DEVICE_CONNECTOR_URI_TEMPLATE;

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);
        if (filter != null) {
            for (Map.Entry<String, Object> e : filter.getAsMap().entrySet()) {
                builder.addQueryParameter(e.getKey(), String.valueOf(e.getValue()));
            }
        }

        builder.addQueryParameter("workspaceId", workspaceId);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<DeviceConnectorPage>() {
        });
        addAuthentication(request);
        return request;
    }

    /**
     * Retrieve a device connector by id
     *
     * @param deviceConnectorId the device connector id
     * @return a device connector
     */
    public Request<DeviceConnector> getById(String deviceConnectorId) {
        final String path = String.format(DEVICE_CONNECTOR_BY_ID_URI_TEMPLATE, deviceConnectorId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<DeviceConnector>() {
        });
        addAuthentication(request);
        return request;
    }

    /**
     * Create a new device connector
     *
     * @param deviceConnector a device connector create command
     * @return the newly created device connector
     */
    public Request<DeviceConnector> create(CreateDeviceConnectorCommand deviceConnector) {
        final String path = DEVICE_CONNECTOR_URI_TEMPLATE;

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<DeviceConnector>() {
        });
        addAuthentication(request);
        request.withBody(deviceConnector);
        return request;
    }

    /**
     * Update a device connector
     *
     * @param deviceConnector a device connector update command
     * @return the updated device connector
     */
    public Request<DeviceConnector> update(UpdateDeviceConnectorCommand deviceConnector) {
        final String path = String.format(DEVICE_CONNECTOR_BY_ID_URI_TEMPLATE, deviceConnector.id());

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.PUT, new TypeReference<DeviceConnector>() {
        });
        addAuthentication(request);
        request.withBody(deviceConnector);
        return request;
    }

    /**
     * Delete a device connector (NOTE: it must not be assigned to any data flows)
     *
     * @param deviceConnectorId the device connector id
     */
    public Request<Void> delete(String deviceConnectorId) {
        final String path = String.format(DEVICE_CONNECTOR_BY_ID_URI_TEMPLATE, deviceConnectorId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.DELETE, new TypeReference<Void>() {
        });
        addAuthentication(request);
        return request;
    }
}
