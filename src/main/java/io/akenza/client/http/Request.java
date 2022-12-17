package io.akenza.client.http;

import io.akenza.client.exceptions.HttpClientResponseException;

import java.util.concurrent.CompletableFuture;

/**
 * Class that represents an HTTP Request that can be executed.
 *
 * @param <T> the type of payload expected in the response after the execution.
 */
public interface Request<T> {

    /**
     * Executes this request synchronously.
     *
     * @return the response body JSON decoded as T
     * @throws HttpClientResponseException if the request was executed but the response wasn't successful.
     * @throws AkenzaException             if the request couldn't be created or executed successfully.
     */
    T execute() throws HttpClientResponseException;

    /**
     * Executes this request asynchronously.
     *
     * @return a {@linkplain CompletableFuture} representing the specified request.
     */
    default CompletableFuture<T> executeAsync() {
        throw new UnsupportedOperationException("executeAsync");
    }
}