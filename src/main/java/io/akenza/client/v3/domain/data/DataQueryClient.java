package io.akenza.client.v3.domain.data;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.HttpOptions;
import io.akenza.client.http.RequestImpl;
import io.akenza.client.utils.BaseClient;
import io.akenza.client.v3.domain.data.queries.DataQuery;
import io.akenza.client.v3.domain.data.queries.ImmutableDataQuery;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.List;

/**
 * Client for querying device data
 */
public class DataQueryClient extends BaseClient {
    private static final String DEVICE_DATA_URI_TEMPLATE = "v3/devices/%s/query";
    private static final String DEVICE_DATA_TOPICS_URI_TEMPLATE = "v3/devices/%s/query/topics";
    private static final String DEVICE_DATA_LAST_SAMPLE_URI_TEMPLATE = "v3/devices/%s/query/latest-sample";

    public DataQueryClient(OkHttpClient client, HttpOptions options) {
        super(client, options);
    }

    /**
     * Query device data for the default filter criteria
     *
     * @param akenzaDeviceId the akenza device id of the device
     * @return a list of device data
     */
    public RequestImpl<List<DeviceData>> query(String akenzaDeviceId) {
        return query(akenzaDeviceId, ImmutableDataQuery.builder().build());
    }

    /**
     * Query device data for the provided filter criteria
     *
     * @param akenzaDeviceId the akenza device id of the device
     * @param query          the filter query
     * @return a list of device data
     */
    public RequestImpl<List<DeviceData>> query(String akenzaDeviceId, DataQuery query) {
        final String path = String.format(DEVICE_DATA_URI_TEMPLATE, akenzaDeviceId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<List<DeviceData>>() {
        });
        addAuthentication(request);
        request.withBody(query);
        return request;
    }

    /**
     * Query the list of topics for a device
     *
     * @param akenzaDeviceId the akenza device id of the device
     * @return a list of topics
     */
    public RequestImpl<List<String>> queryTopics(String akenzaDeviceId) {
        final String path = String.format(DEVICE_DATA_TOPICS_URI_TEMPLATE, akenzaDeviceId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<List<String>>() {
        });
        addAuthentication(request);
        return request;
    }

    /**
     * Query the latest data point for a device
     *
     * @param akenzaDeviceId the akenza device id of the device
     * @return the latest data point
     */
    public RequestImpl<DeviceData> queryLatestSample(String akenzaDeviceId) {
        final String path = String.format(DEVICE_DATA_LAST_SAMPLE_URI_TEMPLATE, akenzaDeviceId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<DeviceData>() {
        });
        addAuthentication(request);
        return request;
    }
}
