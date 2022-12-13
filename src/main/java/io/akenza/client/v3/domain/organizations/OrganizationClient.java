package io.akenza.client.v3.domain.organizations;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.v3.domain.organizations.Organization;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import io.akenza.client.utils.BaseClient;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Client for interacting with organizations
 */
public class OrganizationClient extends BaseClient {
    private static final String ORGANIZATION_URI_TEMPLATE = "v3/organizations/%s";

    public OrganizationClient(OkHttpClient client, HttpUrl baseUrl, String apiKey) {
        super(client, baseUrl, apiKey);
    }

    /**
     * Retrieve the organization of the api key by id
     *
     * @param organizationId the akenza organization id
     * @return an organization
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
}
