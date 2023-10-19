package io.akenza.client.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.type.MapType;
import io.akenza.client.exceptions.AkenzaException;
import io.akenza.client.exceptions.HttpClientResponseException;
import io.akenza.client.exceptions.RateLimitException;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class RequestImpl<T> implements Request<T> {
    private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
    private static final int STATUS_CODE_TOO_MANY_REQUEST = 429;
    private static final int PERMANENT_REDIRECT = 301;
    private static final int TEMPORARY_REDIRECT = 307;

    private final Json json = Json.create();

    private final OkHttpClient client;
    private final String url;
    private final HttpMethod method;
    private final Map<String, String> headers;
    private final TypeReference<T> tType;
    private Object body;

    public RequestImpl(OkHttpClient client, String url, HttpMethod method, TypeReference<T> tType) {
        this.client = client;
        this.url = url;
        this.method = method;
        this.headers = new HashMap<>();
        this.tType = tType;
    }

    /**
     * Executes this request.
     *
     * @return the response body JSON decoded as T
     * @throws AkenzaException if the request execution fails.
     */
    public T execute() throws AkenzaException {
        okhttp3.Request request = createRequest();
        try (Response response = client.newCall(request).execute()) {
            return parseResponse(response);
        } catch (AkenzaException e) {
            throw e;
        } catch (IOException e) {
            throw new AkenzaException("Failed to execute request", e);
        }
    }

    @Override
    public CompletableFuture<T> executeAsync() {
        final CompletableFuture<T> future = new CompletableFuture<>();

        okhttp3.Request request;
        try {
            request = createRequest();
        } catch (AkenzaException e) {
            future.completeExceptionally(e);
            return future;
        }

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException ex) {
                future.completeExceptionally(new AkenzaException("Failed to execute request", ex));
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                try {
                    T parsedResponse = parseResponse(response);
                    future.complete(parsedResponse);
                } catch (AkenzaException ex) {
                    future.completeExceptionally(ex);
                }
            }
        });

        return future;
    }


    protected okhttp3.Request createRequest() throws AkenzaException {
        RequestBody requestBody;
        try {
            requestBody = this.createRequestBody();
        } catch (IOException e) {
            throw new AkenzaException("Couldn't create the request body.", e);
        }
        okhttp3.Request.Builder builder = new okhttp3.Request.Builder()
                .url(url)
                .method(method.value(), requestBody);
        for (Map.Entry<String, String> e : headers.entrySet()) {
            builder.addHeader(e.getKey(), e.getValue());
        }
        builder.addHeader("Content-Type", CONTENT_TYPE_APPLICATION_JSON);
        return builder.build();
    }

    protected RequestBody createRequestBody() throws IOException {
        if (body == null) {
            return null;
        }
        byte[] jsonBody = json.toJson(body);
        return RequestBody.create(jsonBody, MediaType.parse(CONTENT_TYPE_APPLICATION_JSON));
    }

    protected T parseResponse(Response response) throws AkenzaException {
        if (!response.isSuccessful()) {
            throw createResponseException(response);
        }

        try (ResponseBody responseBody = response.body()) {
            return readResponseBody(responseBody);
        } catch (IOException ex) {
            throw new HttpClientResponseException(response.request().url().url().getPath(), response.code(), "Failed to parse the response body.", ex);
        }
    }

    protected T readResponseBody(ResponseBody body) throws IOException {
        String payload = body.string();

        //handle no content
        if (payload.isEmpty()) {
            return null;
        }

        return json.fromJson(payload, tType);
    }

    /**
     * Adds an HTTP header to the request
     *
     * @param name  the name of the header
     * @param value the value of the header
     */
    public RequestImpl<T> withHeader(String name, String value) {
        headers.put(name, value);
        return this;
    }

    public RequestImpl<T> withBody(Object value) {
        body = value;
        return this;
    }

    /**
     * Responsible for parsing an unsuccessful request (status code other than 200)
     * and generating a developer-friendly exception with the error details.
     *
     * @param response the unsuccessful response, as received. If its body is accessed, the buffer must be closed.
     * @return the exception with the error details.
     */
    protected AkenzaException createResponseException(Response response) {
        if (response.code() == STATUS_CODE_TOO_MANY_REQUEST) {
            return createRateLimitException(response);
        }

        String payload = null;
        try (ResponseBody responseBody = response.body()) {
            payload = responseBody.string();
            MapType mapType = json.getMapper().getTypeFactory().constructMapType(HashMap.class, String.class, Object.class);
            Map<String, Object> values = json.fromJson(payload, mapType);
            return new HttpClientResponseException(response.request().url().url().getPath(), response.code(), values);
        } catch (IOException ex) {
            return new HttpClientResponseException(response.request().url().url().getPath(), response.code(), payload, ex);
        }
    }

    private RateLimitException createRateLimitException(Response response) {
        // -1 as default value if the header could not be found.
        long limit = Long.parseLong(response.header("X-RateLimit-Limit", "-1"));
        long remaining = Long.parseLong(response.header("X-RateLimit-Remaining", "-1"));
        long reset = Long.parseLong(response.header("X-RateLimit-Reset", "-1"));
        return new RateLimitException(limit, remaining, reset);
    }
}