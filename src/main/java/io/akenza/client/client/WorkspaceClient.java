package io.akenza.client.client;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.domain.workspaces.Workspace;
import io.akenza.client.domain.workspaces.WorkspaceFilter;
import io.akenza.client.domain.workspaces.WorkspacePage;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.Map;

/**
 * Workspace API client
 */
@Slf4j
public class WorkspaceClient extends BaseClient {
    private static final String WORKSPACE_URI_TEMPLATE = "v3/workspaces/%s";

    public WorkspaceClient(OkHttpClient client, HttpUrl baseUrl, String apiKey) {
        super(client, baseUrl, apiKey);
    }

    //TODO make pagination work
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
        //TODO implement enum for HTTP methods
        var request = new RequestImpl<>(client, url, "GET", new TypeReference<WorkspacePage>() {
        });
        request.withHeader("x-api-key", apiKey);
        return request;
    }

    public Request<Workspace> getById(String workspaceId) {
        final String path = String.format(WORKSPACE_URI_TEMPLATE, workspaceId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, "GET", new TypeReference<Workspace>() {
        });
        request.withHeader("x-api-key", apiKey);
        return request;
    }

    //TODO implement CreateWorkspaceCommand or find out how to best use Immutables for that
    public Request<Workspace> create(Workspace workspace) {
        final String path = String.format(WORKSPACE_URI_TEMPLATE, "");

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, "POST", new TypeReference<Workspace>() {
        });
        request.withHeader("x-api-key", apiKey);
        request.withBody(workspace);
        return request;
    }

    public Request<Workspace> update(Workspace workspace) {
        final String path = String.format(WORKSPACE_URI_TEMPLATE, workspace.id());

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, "PUT", new TypeReference<Workspace>() {
        });
        request.withHeader("x-api-key", apiKey);
        request.withBody(workspace);
        return request;
    }

    public Request<Workspace> delete(String workspaceId) {
        final String path = String.format(WORKSPACE_URI_TEMPLATE, workspaceId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, "DELETE", new TypeReference<Workspace>() {
        });
        request.withHeader("x-api-key", apiKey);
        return request;
    }
}
