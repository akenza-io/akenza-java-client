package io.akenza.client.utils;

import io.akenza.client.http.HttpOptions;
import io.akenza.client.http.RequestImpl;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public abstract class BaseClient {
    protected static final String X_API_KEY = "x-api-key";

    protected final OkHttpClient client;
    protected final HttpUrl baseUrl;
    private final HttpOptions options;

    protected BaseClient(OkHttpClient client, HttpOptions options) {
        this.client = client;
        this.baseUrl = HttpUrl.parse(options.baseUrl());
        this.options = options;
    }

    protected <T> void addAuthentication(RequestImpl<T> request) {
        if (options.authOptions().apiKey() != null) {
            request.withHeader(X_API_KEY, options.authOptions().apiKey());
        } else if (options.authOptions().bearerToken() != null) {
            request.withHeader("Authorization", String.format("Bearer %s", options.authOptions().bearerToken()));
        }
    }
}