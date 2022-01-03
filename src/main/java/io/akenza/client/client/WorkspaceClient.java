package io.akenza.client.client;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.domain.workspaces.*;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.Map;

/**
 * Workspace API client
 */
public class WorkspaceClient extends BaseClient {
    private static final String WORKSPACE_URI_TEMPLATE = "v3/workspaces/%s";
    public static final String X_API_KEY = "x-api-key";

    public WorkspaceClient(OkHttpClient client, HttpUrl baseUrl, String apiKey) {
        super(client, baseUrl, apiKey);
    }

    public Request<WorkspacePage> list(String organizationId, WorkspaceFilter filter) {
        final String path = String.format(WORKSPACE_URI_TEMPLATE, "");

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
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<WorkspacePage>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        return request;
    }

    public Request<Workspace> getById(String workspaceId) {
        final String path = String.format(WORKSPACE_URI_TEMPLATE, workspaceId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<Workspace>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        return request;
    }

    public Request<Workspace> create(CreateWorkspaceCommand workspace) {
        final String path = String.format(WORKSPACE_URI_TEMPLATE, "");

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<Workspace>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        request.withBody(workspace);
        return request;
    }

    public Request<Workspace> update(ImmutableUpdateWorkspaceCommand workspace) {
        final String path = String.format(WORKSPACE_URI_TEMPLATE, workspace.id());

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.PUT, new TypeReference<Workspace>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        request.withBody(workspace);
        return request;
    }

    public Request<Void> delete(String workspaceId) {
        final String path = String.format(WORKSPACE_URI_TEMPLATE, workspaceId);

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
