package io.akenza.client.v3.domain.device_configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import io.akenza.client.utils.BaseClient;
import io.akenza.client.v3.domain.common.MessageResponse;
import io.akenza.client.v3.domain.device_configuration.commands.CreateDeviceConfigurationCommand;
import io.akenza.client.v3.domain.device_configuration.queries.DeviceConfigurationFilter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.Map;

/**
 * Client for interacting with device configurations (currently only supported for MQTT devices)
 */
public class DeviceConfigurationClient extends BaseClient {
    private static final String DEVICE_CONFIGURATION_URI_TEMPLATE = "v3/devices/%s/configuration";
    private static final String DEVICE_CONNECTOR_BY_VERSION_URI_TEMPLATE = "v3/devices/%s/configuration/%s";

    public DeviceConfigurationClient(OkHttpClient client, HttpUrl baseUrl, String apiKey) {
        super(client, baseUrl, apiKey);
    }

    /**
     * Retrieve a list of device configurations for the specified device
     *
     * @param workspaceId the workspace id
     * @param deviceId    the akenza device id
     * @param filter      an optional filter for restricting the search and providing pagination parameters
     * @return a page with device configurations
     */
    public Request<DeviceConfigurationPage> list(String workspaceId, String deviceId, DeviceConfigurationFilter filter) {
        final String path = String.format(DEVICE_CONFIGURATION_URI_TEMPLATE, deviceId);

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
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<DeviceConfigurationPage>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        return request;
    }

    /**
     * Retrieve a device configuration by version
     *
     * @param deviceId the akenza device id
     * @param version  the device configuration version
     * @return a device configuration
     */
    public Request<DeviceConfiguration> getByVersion(String deviceId, Integer version) {
        final String path = String.format(DEVICE_CONNECTOR_BY_VERSION_URI_TEMPLATE, deviceId, version);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<DeviceConfiguration>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        return request;
    }

    /**
     * Retrieve the most recent device configuration for a device
     *
     * @param deviceId the akenza device id
     * @return a device configuration
     */
    public Request<DeviceConfiguration> getLatest(String deviceId) {
        final String path = String.format(DEVICE_CONNECTOR_BY_VERSION_URI_TEMPLATE, deviceId, "latest");

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<DeviceConfiguration>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        return request;
    }


    /**
     * Create a new device configuration
     *
     * @param deviceId            the akenza device id
     * @param deviceConfiguration a device configuration create command
     * @return the newly created device configuration
     */
    public Request<DeviceConfiguration> create(String deviceId, CreateDeviceConfigurationCommand deviceConfiguration) {
        final String path = String.format(DEVICE_CONFIGURATION_URI_TEMPLATE, deviceId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<DeviceConfiguration>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        request.withBody(deviceConfiguration);
        return request;
    }


    /**
     * Push the latest configuration version to the device
     *
     * @param deviceId the akenza device id
     * @return the newly created device configuration
     */
    public Request<MessageResponse> push(String deviceId) {
        final String path = String.format(DEVICE_CONNECTOR_BY_VERSION_URI_TEMPLATE, deviceId, "push");

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<MessageResponse>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        request.withBody(Map.of());
        return request;
    }

    /**
     * Delete a device configuration by version
     *
     * @param deviceId the akenza device id
     * @param version  the device configuration version
     */
    public Request<Void> delete(String deviceId, Integer version) {
        final String path = String.format(DEVICE_CONNECTOR_BY_VERSION_URI_TEMPLATE, deviceId, version);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.DELETE, new TypeReference<Void>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        return request;
    }
}
