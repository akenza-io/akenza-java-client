package io.akenza.client.v3.domain.operations;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.HttpOptions;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import io.akenza.client.utils.BaseClient;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Client for interacting with operations (checking status of background processes and bulk operations)
 */
public class OperationClient extends BaseClient {
    private static final String OPERATION_BY_ID_URI_TEMPLATE = "v3/operations/%s";

    public OperationClient(OkHttpClient client, HttpOptions options) {
        super(client, options);
    }

    /**
     * Retrieve an operation by id
     *
     * @param operationId the operation id
     * @return an operation
     */
    public Request<Operation> getById(String operationId) {
        final String path = String.format(OPERATION_BY_ID_URI_TEMPLATE, operationId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<Operation>() {
        });
        addAuthentication(request);
        return request;
    }
}
