package io.akenza.client.v3.domain.tags;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import io.akenza.client.utils.BaseClient;
import io.akenza.client.v3.domain.tags.commands.CreateTagCommand;
import io.akenza.client.v3.domain.tags.commands.UpdateTagCommand;
import io.akenza.client.v3.domain.tags.queries.TagFilter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.Map;

/**
 * Client for interacting with tags
 */
public class TagClient extends BaseClient {
    private static final String TAG_URI_TEMPLATE = "v3/tags";
    private static final String TAG_BY_ID_URI_TEMPLATE = "v3/tags/%s";

    public TagClient(OkHttpClient client, HttpUrl baseUrl, String apiKey) {
        super(client, baseUrl, apiKey);
    }

    /**
     * Retrieve a list of tags for the specified workspace
     *
     * @param workspaceId the workspace id
     * @param filter      an optional filter for restricting the search and providing pagination parameters
     * @return a page with tags
     */
    public Request<TagPage> list(String workspaceId, TagFilter filter) {
        final String path = TAG_URI_TEMPLATE;

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
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<TagPage>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        return request;
    }

    /**
     * Retrieve a tag by id
     *
     * @param tagId the tag id
     * @return a tag
     */
    public Request<Tag> getById(String tagId) {
        final String path = String.format(TAG_BY_ID_URI_TEMPLATE, tagId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<Tag>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        return request;
    }

    /**
     * Create a new tag
     *
     * @param tag a tag create command
     * @return the newly created tag
     */
    public Request<Tag> create(CreateTagCommand tag) {
        final String path = TAG_URI_TEMPLATE;

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<Tag>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        request.withBody(tag);
        return request;
    }

    /**
     * Update a tag
     *
     * @param tag a tag update command
     * @return the updated tag
     */
    public Request<Tag> update(UpdateTagCommand tag) {
        final String path = String.format(TAG_BY_ID_URI_TEMPLATE, tag.id());

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.PUT, new TypeReference<Tag>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        request.withBody(tag);
        return request;
    }

    /**
     * Delete a tag
     *
     * @param tagId the tag id
     */
    public Request<Void> delete(String tagId) {
        final String path = String.format(TAG_BY_ID_URI_TEMPLATE, tagId);

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
