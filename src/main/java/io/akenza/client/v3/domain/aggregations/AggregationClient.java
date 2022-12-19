package io.akenza.client.v3.domain.aggregations;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.HttpOptions;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import io.akenza.client.utils.BaseClient;
import io.akenza.client.v3.domain.aggregations.objects.KpiAggregationResult;
import io.akenza.client.v3.domain.aggregations.objects.TimeSeriesAggregationResult;
import io.akenza.client.v3.domain.aggregations.queries.AggregationQuery;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.time.temporal.ChronoUnit;

/**
 * Client for querying aggregations
 */
public class AggregationClient extends BaseClient {
    private static final String AGGREGATIONS_URI_TEMPLATE = "v3/devices/%s/query";

    public AggregationClient(OkHttpClient client, HttpOptions options) {
        super(client, options);
    }

    /**
     * Retrieves a time series for the selected dataKey, topic and device
     * If the interval is bigger than 24 it will automatically fetch data that is aggregated (hourly buckets)
     *
     * @param deviceId the device id
     * @param query    the aggregation query
     * @return a time series aggregation result
     */
    public Request<TimeSeriesAggregationResult> timeSeries(String deviceId, AggregationQuery query) {
        String path;
        if (query.interval().from().until(query.interval().to(), ChronoUnit.HOURS) > 25) {
            path = String.format(AGGREGATIONS_URI_TEMPLATE, deviceId) + "/aggregated/hourly/time-series";
        } else {
            path = String.format(AGGREGATIONS_URI_TEMPLATE, deviceId) + "/raw/time-series";
        }

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<TimeSeriesAggregationResult>() {
        });
        addAuthentication(request);
        request.withBody(query);
        return request;
    }

    /**
     * Retrieves an aggregated value for the selected dataKey, topic and device
     * If the interval is bigger than 24 it will automatically fetch data that is aggregated (hourly buckets)
     *
     * @param deviceId the device id
     * @param query    the aggregation query
     * @return a kpi aggregation result
     */
    public Request<KpiAggregationResult> kpi(String deviceId, AggregationQuery query) {
        String path;
        if (query.interval().from().until(query.interval().to(), ChronoUnit.HOURS) > 25) {
            path = String.format(AGGREGATIONS_URI_TEMPLATE, deviceId) + "/aggregated/hourly/kpi";
        } else {
            path = String.format(AGGREGATIONS_URI_TEMPLATE, deviceId) + "/raw/kpi";
        }

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<KpiAggregationResult>() {
        });
        addAuthentication(request);
        request.withBody(query);
        return request;
    }
}
