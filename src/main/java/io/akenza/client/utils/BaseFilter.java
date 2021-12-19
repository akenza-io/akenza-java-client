package io.akenza.client.utils;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseFilter {
    protected final Map<String, Object> parameters = new HashMap<>();

    public Map<String, Object> getAsMap() {
        return parameters;
    }
}