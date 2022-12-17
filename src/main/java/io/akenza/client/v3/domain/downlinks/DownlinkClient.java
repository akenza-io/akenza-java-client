package io.akenza.client.v3.domain.downlinks;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import io.akenza.client.utils.BaseClient;
import io.akenza.client.v3.domain.downlinks.commands.DownlinkCommand;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Client for interacting with data flows
 */
public class DownlinkClient extends BaseClient {
    private static final String DOWNLINK_URI_TEMPLATE = "v3/devices/%s/downlink";

    public DownlinkClient(OkHttpClient client, HttpUrl baseUrl, String apiKey) {
        super(client, baseUrl, apiKey);
    }

    /**
     * Sends a downlink for the specified device
     *
     * @param deviceId the akenza device id
     * @param command  the downlink command
     * @return an empty response
     */
    public Request<Void> send(String deviceId, DownlinkCommand command) {
        final String path = String.format(DOWNLINK_URI_TEMPLATE, deviceId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<Void>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        request.withBody(command);
        return request;
    }
}
