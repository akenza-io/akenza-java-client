package io.akenza.client.v3.domain.custom_fields;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.HttpOptions;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import io.akenza.client.utils.BaseClient;
import io.akenza.client.v3.domain.custom_fields.commands.CreateCustomFieldMetadataCommand;
import io.akenza.client.v3.domain.custom_fields.commands.UpdateCustomFieldMetadataCommand;
import io.akenza.client.v3.domain.custom_fields.queries.CustomFieldFilter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.Map;

/**
 * Client for interacting with custom fields
 */
public class CustomFieldClient extends BaseClient {
    private static final String CUSTOM_FIELD_META_URI_TEMPLATE = "v3/custom-fields/meta";
    private static final String CUSTOM_FIELD_META_BY_ID_URI_TEMPLATE = "v3/custom-fields/meta/%s";

    public CustomFieldClient(OkHttpClient client, HttpOptions options) {
        super(client, options);
    }

    /**
     * Retrieve a list of custom fields for the specified workspace
     *
     * @param workspaceId the workspace id
     * @param filter      an optional filter for restricting the search and providing pagination parameters
     * @return a page with custom field metadata
     */
    public Request<CustomFieldPage> listMetadata(String workspaceId, CustomFieldFilter filter) {
        final String path = CUSTOM_FIELD_META_URI_TEMPLATE;

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
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<CustomFieldPage>() {
        });
        addAuthentication(request);
        return request;
    }

    /**
     * Retrieve a custom field by id
     *
     * @param customFieldId the custom field metadata id
     * @return a custom field metadata
     */
    public Request<CustomFieldMetadata> getMetadataById(String customFieldId) {
        final String path = String.format(CUSTOM_FIELD_META_BY_ID_URI_TEMPLATE, customFieldId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<CustomFieldMetadata>() {
        });
        addAuthentication(request);
        return request;
    }

    /**
     * Create a new custom field metadata
     *
     * @param customField a custom field metadata create command
     * @return the newly created custom field metadata
     */
    public Request<CustomFieldMetadata> createMetadata(CreateCustomFieldMetadataCommand customField) {
        final String path = CUSTOM_FIELD_META_URI_TEMPLATE;

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<CustomFieldMetadata>() {
        });
        addAuthentication(request);
        request.withBody(customField);
        return request;
    }

    /**
     * Update a custom field metadata
     *
     * @param customField a custom field metadata update command
     * @return the updated custom field metadata
     */
    public Request<CustomFieldMetadata> updateMetadata(UpdateCustomFieldMetadataCommand customField) {
        final String path = String.format(CUSTOM_FIELD_META_BY_ID_URI_TEMPLATE, customField.id());

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.PUT, new TypeReference<CustomFieldMetadata>() {
        });
        addAuthentication(request);
        request.withBody(customField);
        return request;
    }

    /**
     * Delete a custom field metadata, this will delete all assignments of this custom field
     *
     * @param customFieldId the custom field metadata id
     */
    public Request<Void> deleteMetadata(String customFieldId) {
        final String path = String.format(CUSTOM_FIELD_META_BY_ID_URI_TEMPLATE, customFieldId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.DELETE, new TypeReference<Void>() {
        });
        addAuthentication(request);
        return request;
    }
}
