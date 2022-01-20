package io.akenza.client.utils;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseFilter {
    protected final Map<String, Object> parameters = new HashMap<>();

    public Map<String, Object> getAsMap() {
        return parameters;
    }

    public BaseFilter withPageSize(Integer size) {
        parameters.put("size", size);
        return this;
    }

    public BaseFilter withPageNumber(Integer page) {
        parameters.put("page", page);
        return this;
    }

    public BaseFilter withSort(SortDirection direction, String property) {
        String sort = String.format("%s,%s", direction.getValue(), property);
        parameters.put("sort", sort);
        return this;
    }
}
