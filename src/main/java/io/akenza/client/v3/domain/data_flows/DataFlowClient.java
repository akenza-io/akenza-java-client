package io.akenza.client.v3.domain.data_flows;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import io.akenza.client.utils.BaseClient;
import io.akenza.client.v3.domain.data_flows.commands.CreateDataFlowCommand;
import io.akenza.client.v3.domain.data_flows.commands.UpdateDataFlowCommand;
import io.akenza.client.v3.domain.data_flows.queries.DataFlowFilter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.Map;

/**
 * Client for interacting with data flows
 */
public class DataFlowClient extends BaseClient {
    private static final String DATA_FLOW_URI_TEMPLATE = "v3/data-flows";
    private static final String DATA_FLOW_BY_ID_URI_TEMPLATE = "v3/data-flows/%s";

    public DataFlowClient(OkHttpClient client, HttpUrl baseUrl, String apiKey) {
        super(client, baseUrl, apiKey);
    }

    /**
     * Retrieve a list of data flows for the specified workspace
     *
     * @param workspaceId the workspace id
     * @param filter      an optional filter for restricting the search and providing pagination parameters
     * @return a page with data flows
     */
    public Request<DataFlowPage> list(String workspaceId, DataFlowFilter filter) {
        final String path = DATA_FLOW_URI_TEMPLATE;

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
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<DataFlowPage>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        return request;
    }

    /**
     * Retrieve a data flow by id
     *
     * @param dataFlowId the data flow id
     * @return a data flow
     */
    public Request<DataFlow> getById(String dataFlowId) {
        final String path = String.format(DATA_FLOW_BY_ID_URI_TEMPLATE, dataFlowId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<DataFlow>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        return request;
    }

    /**
     * Create a new data flow
     *
     * @param dataFlow a data flow create command
     * @return the newly created data flow
     */
    public Request<DataFlow> create(CreateDataFlowCommand dataFlow) {
        final String path = DATA_FLOW_URI_TEMPLATE;

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<DataFlow>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        request.withBody(dataFlow);
        return request;
    }

    /**
     * Update a data flow
     *
     * @param dataFlow a data flow update command
     * @return the updated data flow
     */
    public Request<DataFlow> update(UpdateDataFlowCommand dataFlow) {
        final String path = String.format(DATA_FLOW_BY_ID_URI_TEMPLATE, dataFlow.id());

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.PUT, new TypeReference<DataFlow>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        request.withBody(dataFlow);
        return request;
    }

    /**
     * Delete a data flow (NOTE: it must not be assigned to any devices)
     *
     * @param dataFlowId the data flow id
     */
    public Request<Void> delete(String dataFlowId) {
        final String path = String.format(DATA_FLOW_BY_ID_URI_TEMPLATE, dataFlowId);

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
