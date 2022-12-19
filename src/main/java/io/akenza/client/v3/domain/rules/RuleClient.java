package io.akenza.client.v3.domain.rules;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.HttpOptions;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import io.akenza.client.utils.BaseClient;
import io.akenza.client.v3.domain.rules.commands.CreateRuleCommand;
import io.akenza.client.v3.domain.rules.commands.UpdateRuleCommand;
import io.akenza.client.v3.domain.rules.queries.RuleFilter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.Map;

/**
 * Client for interacting with rules
 */
public class RuleClient extends BaseClient {
    private static final String RULE_URI_TEMPLATE = "v3/rules";
    private static final String RULE_BY_ID_URI_TEMPLATE = "v3/rules/%s";

    public RuleClient(OkHttpClient client, HttpOptions options) {
        super(client, options);
    }

    /**
     * Retrieve a list of rules for the specified workspace
     *
     * @param workspaceId the workspace id
     * @param filter      an optional filter for restricting the search and providing pagination parameters
     * @return a page with rules
     */
    public Request<RulePage> list(String workspaceId, RuleFilter filter) {
        final String path = RULE_URI_TEMPLATE;

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
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<RulePage>() {
        });
        addAuthentication(request);
        return request;
    }

    /**
     * Retrieve a rule by id
     *
     * @param ruleId the device connector id
     * @return a rule
     */
    public Request<Rule> getById(String ruleId) {
        final String path = String.format(RULE_BY_ID_URI_TEMPLATE, ruleId);

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.GET, new TypeReference<Rule>() {
        });
        addAuthentication(request);
        return request;
    }

    /**
     * Create a new rule
     *
     * @param rule a rule create command
     * @return the newly created rule
     */
    public Request<Rule> create(CreateRuleCommand rule) {
        final String path = RULE_URI_TEMPLATE;

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<Rule>() {
        });
        addAuthentication(request);
        request.withBody(rule);
        return request;
    }

    /**
     * Update a rule
     *
     * @param rule a rule update command
     * @return the updated rule
     */
    public Request<Rule> update(UpdateRuleCommand rule) {
        final String path = String.format(RULE_BY_ID_URI_TEMPLATE, rule.id());

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.PUT, new TypeReference<Rule>() {
        });
        addAuthentication(request);
        request.withBody(rule);
        return request;
    }

    /**
     * Delete a rule
     *
     * @param ruleId the rule id
     */
    public Request<Void> delete(String ruleId) {
        final String path = String.format(RULE_BY_ID_URI_TEMPLATE, ruleId);

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
