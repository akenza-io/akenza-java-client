package io.akenza.client.v3.domain.output_connectors;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import io.akenza.client.utils.BaseClient;
import io.akenza.client.v3.domain.output_connectors.commands.CreateOutputConnectorCommand;
import io.akenza.client.v3.domain.output_connectors.commands.UpdateOutputConnectorCommand;
import io.akenza.client.v3.domain.output_connectors.queries.OutputConnectorFilter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.Map;

/**
 * Client for interacting with output connectors
 */
public class OutputConnectorClient extends BaseClient {
    private static final String OUTPUT_CONNECTOR_URI_TEMPLATE = "v3/output-connectors";
    private static final String OUTPUT_CONNECTOR_BY_ID_URI_TEMPLATE = "v3/output-connectors/%s";

    public OutputConnectorClient(OkHttpClient client, HttpUrl baseUrl, String apiKey) {
        super(client, baseUrl, apiKey);
    }

    /**
     * Retrieve a list of output connectors for the specified workspace
     *
     * @param workspaceId the workspace id
     * @param filter      an optional filter for restricting the search and providing pagination parameters
     * @return a page with output connectors
     */
    public Request<OutputConnectorPage> list(String workspaceId, OutputConnectorFilter filter) {
        final String path = OUTPUT_CONNECTOR_URI_TEMPLATE;

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
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<OutputConnectorPage>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        return request;
    }

    /**
     * Retrieve an output connector by id
     *
     * @param outputConnectorId the output connector id
     * @return an output connector
     */
    public Request<OutputConnector> getById(String outputConnectorId) {
        final String path = String.format(OUTPUT_CONNECTOR_BY_ID_URI_TEMPLATE, outputConnectorId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<OutputConnector>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        return request;
    }

    /**
     * Create a new output connector
     *
     * @param outputConnector an output connector create command
     * @return the newly created output connector
     */
    public Request<OutputConnector> create(CreateOutputConnectorCommand outputConnector) {
        final String path = OUTPUT_CONNECTOR_URI_TEMPLATE;

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<OutputConnector>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        request.withBody(outputConnector);
        return request;
    }

    /**
     * Update an output connector
     *
     * @param outputConnector an output connector update command
     * @return the updated output connector
     */
    public Request<OutputConnector> update(UpdateOutputConnectorCommand outputConnector) {
        final String path = String.format(OUTPUT_CONNECTOR_BY_ID_URI_TEMPLATE, outputConnector.id());

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.PUT, new TypeReference<OutputConnector>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        request.withBody(outputConnector);
        return request;
    }

    /**
     * Delete an output connector (NOTE: it must not be assigned to a data flow)
     *
     * @param outputConnectorId the data flow id
     */
    public Request<Void> delete(String outputConnectorId) {
        final String path = String.format(OUTPUT_CONNECTOR_BY_ID_URI_TEMPLATE, outputConnectorId);

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
