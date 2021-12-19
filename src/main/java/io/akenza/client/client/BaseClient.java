package io.akenza.client.client;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

abstract class BaseClient {
    protected final OkHttpClient client;
    protected final HttpUrl baseUrl;
    protected final String apiKey;

    BaseClient(OkHttpClient client, HttpUrl baseUrl, String apiKey) {
        this.client = client;
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }
}