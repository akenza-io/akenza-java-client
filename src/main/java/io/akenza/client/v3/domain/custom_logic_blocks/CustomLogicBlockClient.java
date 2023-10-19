package io.akenza.client.v3.domain.custom_logic_blocks;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.HttpOptions;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import io.akenza.client.utils.BaseClient;
import io.akenza.client.v3.domain.custom_logic_blocks.commands.CreateCustomLogicBlockCommand;
import io.akenza.client.v3.domain.custom_logic_blocks.commands.UpdateCustomLogicBlockCommand;
import io.akenza.client.v3.domain.custom_logic_blocks.queries.CustomLogicBlockFilter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.Map;

/**
 * Client for interacting with custom logic blocks
 */
public class CustomLogicBlockClient extends BaseClient {
    private static final String CUSTOM_LOGIC_BLOCK_URI_TEMPLATE = "v3/rules/custom";
    private static final String CUSTOM_LOGIC_BLOCK_BY_ID_URI_TEMPLATE = "v3/rules/custom/%s";

    public CustomLogicBlockClient(OkHttpClient client, HttpOptions options) {
        super(client, options);
    }

    /**
     * Retrieve a list of custom logic blocks for the specified organization
     *
     * @param organizationId the organization id
     * @param filter         an optional filter for restricting the search and providing pagination parameters
     * @return a page with custom logic blocks
     */
    public Request<CustomLogicBlockPage> list(String organizationId, CustomLogicBlockFilter filter) {

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(CUSTOM_LOGIC_BLOCK_URI_TEMPLATE);
        if (filter != null) {
            for (Map.Entry<String, Object> e : filter.getAsMap().entrySet()) {
                builder.addQueryParameter(e.getKey(), String.valueOf(e.getValue()));
            }
        }

        builder.addQueryParameter("organizationId", organizationId);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<CustomLogicBlockPage>() {
        });
        addAuthentication(request);
        return request;
    }

    /**
     * Retrieve a custom logic block by id
     *
     * @param customLogicBlockId the custom logic block id
     * @return a custom logic block
     */
    public Request<CustomLogicBlockDetails> getById(String customLogicBlockId) {
        final String path = String.format(CUSTOM_LOGIC_BLOCK_BY_ID_URI_TEMPLATE, customLogicBlockId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<CustomLogicBlockDetails>() {
        });
        addAuthentication(request);
        return request;
    }

    /**
     * Create a new custom logic block
     *
     * @param customLogicBlock a custom logic block create command
     * @return the newly created custom logic block
     */
    public Request<CustomLogicBlock> create(CreateCustomLogicBlockCommand customLogicBlock) {

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(CUSTOM_LOGIC_BLOCK_URI_TEMPLATE);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<CustomLogicBlock>() {
        });
        addAuthentication(request);
        request.withBody(customLogicBlock);
        return request;
    }

    /**
     * Update a custom logic block
     *
     * @param customLogicBlock a custom logic block update command
     * @return the updated custom logic block
     */
    public Request<CustomLogicBlock> update(UpdateCustomLogicBlockCommand customLogicBlock) {
        final String path = String.format(CUSTOM_LOGIC_BLOCK_BY_ID_URI_TEMPLATE, customLogicBlock.id());

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.PUT, new TypeReference<CustomLogicBlock>() {
        });
        addAuthentication(request);
        request.withBody(customLogicBlock);
        return request;
    }

    /**
     * Delete a custom logic block (NOTE: it should not be used in any rules)
     *
     * @param customLogicBlockId the custom logic block id
     */
    public Request<Void> delete(String customLogicBlockId) {
        final String path = String.format(CUSTOM_LOGIC_BLOCK_BY_ID_URI_TEMPLATE, customLogicBlockId);

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
