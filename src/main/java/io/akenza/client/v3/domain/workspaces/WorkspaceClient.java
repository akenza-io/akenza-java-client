package io.akenza.client.v3.domain.workspaces;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.v3.domain.workspaces.WorkspacePage;
import io.akenza.client.v3.domain.workspaces.commands.CreateWorkspaceCommand;
import io.akenza.client.v3.domain.workspaces.commands.UpdateWorkspaceCommand;
import io.akenza.client.v3.domain.workspaces.queries.WorkspaceFilter;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import io.akenza.client.utils.BaseClient;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.Map;

/**
 * Client for interacting with workspaces
 */
public class WorkspaceClient extends BaseClient {
    private static final String WORKSPACE_URI_TEMPLATE = "v3/workspaces";
    private static final String WORKSPACE_BY_ID_URI_TEMPLATE = "v3/workspaces/%s";

    public WorkspaceClient(OkHttpClient client, HttpUrl baseUrl, String apiKey) {
        super(client, baseUrl, apiKey);
    }

    /**
     * Retrieve a list of workspaces for the specified organization
     *
     * @param organizationId the akenza organization id
     * @param filter         an optional filter for restricting the search and providing pagination parameters
     * @return a page with workspace
     */
    public Request<WorkspacePage> list(String organizationId, WorkspaceFilter filter) {
        final String path = WORKSPACE_URI_TEMPLATE;

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

    /**
     * Retrieve a workspace by id
     *
     * @param workspaceId the akenza workspace id
     * @return a workspace
     */
    public Request<Workspace> getById(String workspaceId) {
        final String path = String.format(WORKSPACE_BY_ID_URI_TEMPLATE, workspaceId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<Workspace>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        return request;
    }

    /**
     * Create a new workspace
     *
     * @param workspace a workspace create command
     * @return the newly created workspace
     */
    public Request<Workspace> create(CreateWorkspaceCommand workspace) {
        final String path = WORKSPACE_URI_TEMPLATE;

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

    /**
     * Update an existing workspace
     *
     * @param workspace a workspace update command
     * @return the updated workspace
     */
    public Request<Workspace> update(UpdateWorkspaceCommand workspace) {
        final String path = String.format(WORKSPACE_BY_ID_URI_TEMPLATE, workspace.id());

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

    /**
     * Delete a workspace
     *
     * @param workspaceId the akenza workspace id
     * @return
     */
    public Request<Void> delete(String workspaceId) {
        final String path = String.format(WORKSPACE_BY_ID_URI_TEMPLATE, workspaceId);

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
