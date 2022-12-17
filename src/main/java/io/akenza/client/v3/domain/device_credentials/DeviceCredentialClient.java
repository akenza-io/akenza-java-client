package io.akenza.client.v3.domain.device_credentials;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import io.akenza.client.utils.BaseClient;
import io.akenza.client.v3.domain.device_credentials.commands.CreateDeviceCredentialCommand;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Client for interacting with device credentials
 */
public class DeviceCredentialClient extends BaseClient {
    private static final String DEVICE_CREDENTIALS_URI_TEMPLATE = "v3/device-authorization/devices/%s/credentials";
    private static final String DEVICE_CREDENTIALS_BY_ID_URI_TEMPLATE = "v3/device-authorization/devices/%s/credentials/%s";

    public DeviceCredentialClient(OkHttpClient client, HttpUrl baseUrl, String apiKey) {
        super(client, baseUrl, apiKey);
    }

    /**
     * Retrieve a list of device credentials for the specified device
     *
     * @param deviceId the akenza device id
     * @return a page with device credentials
     */
    public Request<DeviceCredentialPage> list(String deviceId) {
        final String path = String.format(DEVICE_CREDENTIALS_URI_TEMPLATE, deviceId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<DeviceCredentialPage>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        return request;
    }

    /**
     * Create a new device credential
     *
     * @param deviceCredential a device credential create command
     * @return the newly created device credential
     */
    public Request<DeviceCredential> create(CreateDeviceCredentialCommand deviceCredential) {
        final String path = String.format(DEVICE_CREDENTIALS_URI_TEMPLATE, deviceCredential.akenzaDeviceId());

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<DeviceCredential>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        request.withBody(deviceCredential);
        return request;
    }

    /**
     * Delete a device credential
     *
     * @param deviceId           the akenza device id
     * @param deviceCredentialId the device credential id
     */
    public Request<Void> delete(String deviceId, String deviceCredentialId) {
        final String path = String.format(DEVICE_CREDENTIALS_BY_ID_URI_TEMPLATE, deviceId, deviceCredentialId);

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
