package io.akenza.client.v3.domain.devices;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.HttpOptions;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import io.akenza.client.utils.BaseClient;
import io.akenza.client.v3.domain.devices.commands.ImmutableCreateDeviceCommand;
import io.akenza.client.v3.domain.devices.commands.ImmutableUpdateDeviceCommand;
import io.akenza.client.v3.domain.devices.queries.DeviceFilter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.Map;

/**
 * Client for querying device data
 */
public class DeviceClient extends BaseClient {
    private static final String DEVICE_URI_TEMPLATE = "v3/devices";
    private static final String DEVICE_BY_ID_URI_TEMPLATE = "v3/devices/%s";
    private static final String ASSET_URI_TEMPLATE = "v3/assets/%s";

    public DeviceClient(OkHttpClient client, HttpOptions options) {
        super(client, options);
    }

    /**
     * Retrieve a list of devices for the specified organization or workspace
     * Either the organization id or the workspace id has to be set
     *
     * @param filter         an optional filter for restricting the search and providing pagination parameters
     * @param workspaceId    an optional workspace id
     * @param organizationId an optional organization id
     * @return a page with devices
     */
    public Request<DevicePage> list(DeviceFilter filter, String workspaceId, String organizationId) {
        final String path = String.format(ASSET_URI_TEMPLATE, "");

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);
        if (filter != null) {
            for (Map.Entry<String, Object> e : filter.getAsMap().entrySet()) {
                builder.addQueryParameter(e.getKey(), String.valueOf(e.getValue()));
            }
        }

        if (organizationId != null) {
            builder.addQueryParameter("organizationId", organizationId);
        }
        if (workspaceId != null) {
            builder.addQueryParameter("workspaceId", workspaceId);
        }
        if (organizationId == null && workspaceId == null) {
            throw new IllegalArgumentException("Ether an organization or workspace id has to be provided");
        }

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<DevicePage>() {
        });
        addAuthentication(request);
        return request;
    }

    /**
     * Retrieve a device by id
     *
     * @param akenzaDeviceId the akenza workspace id
     * @return a device
     */
    public Request<Device> getById(String akenzaDeviceId) {
        final String path = String.format(ASSET_URI_TEMPLATE, akenzaDeviceId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<Device>() {
        });
        addAuthentication(request);
        return request;
    }

    /**
     * Create a new device
     *
     * @param device a device create command
     * @return the newly created device
     */
    public Request<Device> create(ImmutableCreateDeviceCommand device) {
        final String path = DEVICE_URI_TEMPLATE;

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<Device>() {
        });
        addAuthentication(request);
        request.withBody(device);

        //TODO make sure that we do not have conflicting models between device and asset
        return request;
    }

    /**
     * Update an existing device
     *
     * @param device a device update command
     * @return the updated device
     */
    public Request<Device> update(ImmutableUpdateDeviceCommand device) {
        final String path = String.format(DEVICE_BY_ID_URI_TEMPLATE, device.id());

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.PUT, new TypeReference<Device>() {
        });
        addAuthentication(request);
        request.withBody(device);

        //TODO make sure that we do not have conflicting models between device and asset
        return request;
    }

    /**
     * Delete a device
     *
     * @param akenzaDeviceId the akenza device id
     * @return an empty response
     */
    public Request<Void> delete(String akenzaDeviceId) {
        final String path = String.format(DEVICE_BY_ID_URI_TEMPLATE, akenzaDeviceId);

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
