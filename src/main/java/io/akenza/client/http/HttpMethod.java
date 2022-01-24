package io.akenza.client.http;

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS");

    private String value;

    HttpMethod(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
