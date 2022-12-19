package io.akenza.client.v3.domain.device_types;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.HttpOptions;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import io.akenza.client.utils.BaseClient;
import io.akenza.client.v3.domain.device_types.commands.CreateDeviceTypeCommand;
import io.akenza.client.v3.domain.device_types.commands.UpdateDeviceTypeCommand;
import io.akenza.client.v3.domain.device_types.queries.DeviceTypeFilter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.Map;

/**
 * Client for interacting with device types
 */
public class DeviceTypeClient extends BaseClient {
    private static final String DEVICE_TYPE_URI_TEMPLATE = "v3/device-types";
    private static final String DEVICE_TYPE_BY_ID_URI_TEMPLATE = "v3/device-types/%s";

    public DeviceTypeClient(OkHttpClient client, HttpOptions options) {
        super(client, options);
    }

    /**
     * Retrieve a list of device types for the specified organization
     *
     * @param organizationId the akenza organization id
     * @param filter         an optional filter for restricting the search and providing pagination parameters
     * @return a page with device types
     */
    public Request<DeviceTypePage> list(String organizationId, DeviceTypeFilter filter) {
        final String path = DEVICE_TYPE_URI_TEMPLATE;

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);
        if (filter != null) {
            for (Map.Entry<String, Object> e : filter.getAsMap().entrySet()) {
                builder.addQueryParameter(e.getKey(), String.valueOf(e.getValue()));
            }
        }

        builder.addQueryParameter("organizationId", organizationId);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<DeviceTypePage>() {
        });
        addAuthentication(request);
        return request;
    }

    /**
     * Retrieve a device type by id
     *
     * @param deviceTypeId the akenza device type id
     * @return a device type
     */
    public Request<DeviceType> getById(String deviceTypeId) {
        final String path = String.format(DEVICE_TYPE_BY_ID_URI_TEMPLATE, deviceTypeId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<DeviceType>() {
        });
        addAuthentication(request);
        return request;
    }

    /**
     * Create a new custom device type
     *
     * @param deviceType a device type create command
     * @return the newly created device type
     */
    public Request<DeviceType> create(CreateDeviceTypeCommand deviceType) {
        final String path = DEVICE_TYPE_URI_TEMPLATE;

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<DeviceType>() {
        });
        addAuthentication(request);
        request.withBody(deviceType);
        return request;
    }

    /**
     * Update a custom device type (library device types cannot be updated, submit a pull request at https://github.com/akenza-io/device-type-library instead)
     *
     * @param deviceType a device type update command
     * @return the updated device type
     */
    public Request<DeviceType> update(UpdateDeviceTypeCommand deviceType) {
        final String path = String.format(DEVICE_TYPE_BY_ID_URI_TEMPLATE, deviceType.id());

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.PUT, new TypeReference<DeviceType>() {
        });
        addAuthentication(request);
        request.withBody(deviceType);
        return request;
    }

    /**
     * Delete a custom device type (library device types cannot be deleted)
     *
     * @param deviceTypeId the device type id
     */
    public Request<Void> delete(String deviceTypeId) {
        final String path = String.format(DEVICE_TYPE_BY_ID_URI_TEMPLATE, deviceTypeId);

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
