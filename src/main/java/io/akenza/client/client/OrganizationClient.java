package io.akenza.client.client;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.domain.organizations.*;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.Map;

/**
 * Client for interacting with organizations
 */
public class OrganizationClient extends BaseClient {
    private static final String ORGANIZATION_URI_TEMPLATE = "v3/organizations/%s";

    public OrganizationClient(OkHttpClient client, HttpUrl baseUrl, String apiKey) {
        super(client, baseUrl, apiKey);
    }

    /**
     * Retrieve a list of organizations for the specified organization
     *
     * @param filter an optional filter for restricting the search and providing pagination parameters
     * @return a page with organization
     */
    public Request<OrganizationPage> list(OrganizationFilter filter) {
        final String path = String.format(ORGANIZATION_URI_TEMPLATE, "");

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);
        if (filter != null) {
            for (Map.Entry<String, Object> e : filter.getAsMap().entrySet()) {
                builder.addQueryParameter(e.getKey(), String.valueOf(e.getValue()));
            }
        }

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<OrganizationPage>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        return request;
    }

    /**
     * Retrieve a organization by id
     *
     * @param organizationId the akenza organization id
     * @return a organization
     */
    public Request<Organization> getById(String organizationId) {
        final String path = String.format(ORGANIZATION_URI_TEMPLATE, organizationId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<Organization>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        return request;
    }

    /**
     * Create a new organization
     *
     * @param organization a organization create command
     * @return the newly created organization
     */
    public Request<Organization> create(CreateOrganizationCommand organization) {
        final String path = String.format(ORGANIZATION_URI_TEMPLATE, "");

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<Organization>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        request.withBody(organization);
        return request;
    }

    /**
     * Update an existing organization
     *
     * @param organization a organization update command
     * @return the updated organization
     */
    public Request<Organization> update(ImmutableUpdateOrganizationCommand organization) {
        final String path = String.format(ORGANIZATION_URI_TEMPLATE, organization.id());

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.PUT, new TypeReference<Organization>() {
        });
        request.withHeader(X_API_KEY, apiKey);
        request.withBody(organization);
        return request;
    }

    /**
     * Delete a organization
     *
     * @param organizationId the akenza organization id
     * @return empty response
     */
    public Request<Void> delete(String organizationId) {
        final String path = String.format(ORGANIZATION_URI_TEMPLATE, organizationId);

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
