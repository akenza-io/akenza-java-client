package io.akenza.client.client;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.domain.data.DataQuery;
import io.akenza.client.domain.data.DeviceData;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.RequestImpl;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.List;

public class DataQueryClient extends BaseClient {
    private static final String DEVICE_DATA_URI_TEMPLATE = "v3/devices/%s/query";
    private static final String DEVICE_DATA_TOPICS_URI_TEMPLATE = "v3/devices/%s/query/topics";
    private static final String DEVICE_DATA_LAST_SAMPLE_URI_TEMPLATE = "v3/devices/%s/query/latest-sample";

    public DataQueryClient(OkHttpClient client, HttpUrl baseUrl, String apiKey) {
        super(client, baseUrl, apiKey);
    }

    public RequestImpl<List<DeviceData>> query(String deviceId, DataQuery query) {
        final String path = String.format(DEVICE_DATA_URI_TEMPLATE, deviceId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<List<DeviceData>>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        request.withBody(query);
        return request;
    }

    public RequestImpl<List<String>> queryTopics(String deviceId) {
        final String path = String.format(DEVICE_DATA_TOPICS_URI_TEMPLATE, deviceId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<List<String>>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        return request;
    }

    public RequestImpl<DeviceData> queryLatestSample(String deviceId) {
        final String path = String.format(DEVICE_DATA_TOPICS_URI_TEMPLATE, deviceId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<DeviceData>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        return request;
    }
}
